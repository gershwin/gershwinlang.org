---
title: Getting Started
---

## Getting Started ##

Gershwin is an extension of Clojure. It helps to start by pointing out how Gershwin differs from Clojure:

 * Gershwin has _words_ instead of named functions
 * Gershwin has _quotations_ instead of anonymous functions
 * Gershwin puts everything on the stack

We will cover these features in more detail in the sections that follow. Note that these features are additive; the goal of Gershwin is to maintain complete compatibility with Clojure while offering stack-based, concatenative features on top of what Clojure already provides.

So if you're not using any of the bulleted features listed above, you're just writing Clojure.

### REPL ###

You can start a Gershwin REPL much like a Clojure one. [Download the latest Gershwin](http://sourceforge.net/projects/gershwin/files) and run the following:

```
java -cp gershwin.jar gershwin.main
```

Like Clojure's default REPL, this provides a bare-bones experience. Use tools like [rlwrap](), [ledit](), or Emacs' inferior Lisp support for a richer editing experience.

### Hello, World ###

The REPL starts and you're greeted with a familiar Clojure-style REPL that indicates you're in the `user` namespace. Now, in addition to having all of Clojure at your fingertips, you also have access to Gershwin.

Try the following at the REPL:

```
user=> 2

--- Data Stack:
2
user=> 3

--- Data Stack:
2
3
user=> +

--- Data Stack:
5
```

You just put the value `2` on the top of the stack, then the value `3`. Finally, you entered the `+` word.

When Gershwin encounters a word, it invokes it. A word takes values off the top of the stack, does something with them, and puts 0 or more values back on the stack. The `+` word takes two values and puts their sum on top of the stack. In this case, the value `5` is all that remains on the top of the stack.

### Defining Words ###

Clojure provides the functions `inc` and `dec` that add or subtract the value `1` from their argument. If these weren't implemented with methods from the lower-level `clojure.lang.Numbers` class, their implementation might look like this:

```
(defn inc [n] (+ n 1))
(defn dec [n] (- n 1))
```

Or by using partial application:

```
(def inc (partial + 1))
(def dec (partial - 1))
```

Here's what they look like in Gershwin:

```
: inc [n -- n] 1 + .
: dec [n -- n] 1 - .
```

Let's break down the word definitions into parts.

The `:` character followed by a space indicates the beginning of a word definition. This is one of the two additions to Clojure syntax that Gershwin introduces.

The next form must be a symbol that names the word, in this case `inc` and `dec`. Symbols can consist of any character legal in Clojure symbols, including dashes, question marks, exclamation points, etc.

The next required element is the _stack effect_ declaration. A stack effect declaration is written as a Clojure vector of symbols, specifying what items are taken off the stack followed by `--` followed by how many items are put back on the stack. While optional in some stack-based languages, Gershwin requires you to indicate what the intended stack effect of a given word will be. Currently this is used for documentation purposes only, but future versions of Gershwin will perform some sanity checks on stack effects in a program based on this information.

**Note:** The stack effect is not a declaration of parameters, nor do the symbols inside the stack effect have any bearing on the word's definition. You can use whatever symbols make most sense to describe the stack effect of your words.

After the stack effect declaration is the main definition of the word, in this case `1 +` and `1 -` for `inc` and `dec` respectively. If this is your first exposure to concatenative languages, you may be wondering where the rest of the function is. Remember that `+` takes two items off the stack and adds them together; similarly, `-` takes two items off the stack and subtracts one from the other. The `inc` and `dec` words simply provide the `+` and `-` words with their second "parameter" and will acquire their first "parameter" when used in a program.

Let's see what it's like to use them:

```
user=> 2 inc

--- Data Stack:
3
```

This expression puts `3` on the top of the stack. But how? Let's replace the word `inc` with its definition and see if it makes it any clearer:

```
user=> 2 1 +

--- Data Stack:
3
```

Now the code looks like our first example at the beginning of this page. Put `2` on the stack, then `1`, then invoke the `+` word which takes those top two values and adds them together, putting the result on the top of the stack. The `inc` word simply captured the `1 +` portion of this program, which behaves no differently than if we entered it all explicitly.

This simple example demonstrates the absolute straight-forwardness of code factoring in a concatenative language. Instead of worrying about defining formal parameters for new functions, passing in arguments and directing the result in the flow of your program, a concatenative program consists of a left-to-right flow that consists of (1) adding data items to the top of the stack and (2) evaluating words that work with that stack. This allows you to replace arbitrary parts of your programs with word definitions simply by cutting-and-pasting your code into new word definitions.

### Quotations ###

In addition to word definitions, the other major conceptual addition that Gershwin makes to the Clojure arsenal is that of _quotations_. Code wrapped in a quotation is not executed immediately; it is only evaluated when the quotation is invoked either explicitly or when used with certain Gershwin core words.

Quotations form the basis of the implementation of Gershwin's shuffle words (core words for moving items around on the top of the stack), conditionals (`if`, `when`, etc.), unit testing framework, and also fulfil the role that function values play in other functional programming languages (e.g., as arguments to `map`, `filter`, etc).

Let's take a look at a simple conditional expression:

```
: is-greater "Print a message based on whether a is greater than or less than b."
  [a b --]
  > #[ "yep, greater" println ] #[ "nope, lesser" println ] if .
```

The word `is-greater` begins with the `>` word that takes the top two items off the stack and puts `true` on the top of the stack if the first item is greater than the second, `false` otherwise.

We then see three forms: two quotations and the `if` word. The `if` word expects to find a value followed by two quotations, the first of which represents the "then" logical branch and the second of which represents the "else" branch. We say that it first expects a "value" and not simply a "boolean," because Gershwin follows Clojure truthy/falsey semantics, such that anything other than `nil` or `false` represents a truthy value for conditional expressions.

So if we were to use `is-greater`:

```
user=> 2 3 is-greater
nope, lesser

--- Data Stack:
```

The `2` and `3` are taken off the stack by the `>` word, which then puts `false` on the top of the stack. The `if` word then consumes that `false` value and the two quotations that follow it. Since the value `false` is falsey, `if` invokes the else-quotation which, in this case, prints the string `"nope, lesser"` followed by a line break.

**Note:** Unlike Clojure functions which always return a value, if only `nil`, Gershwin allows for words that do not take items off or put items on the stack.

### Clojure Interop ###

As mentioned above, compatibility with Clojure is Gershwin's prime directive. Most words in Gershwin core are implemented as direct delegates to core Clojure functions. Let's take a look at how the `+` word is defined:

```
: + [n n -- n] (+ (pop-it) (pop-it)) .
```

This looks like the other word definitions we've seen so far, except its entire implementation is a Clojure form, `(+ (pop-it) (pop-it))`. Since this is neither a word or a quotation, Gershwin delegates the reading and evaluation of this to Clojure and puts the return value on the top of the stack. The `pop-it` function is a low-level utility that removes the top-most item of the stack, so we're simply popping the two top-most items and passing them as arguments to the `clojure.core/+` function.

How can we use the `+` symbol both as a word definition and a Clojure function? Inside of a Clojure expression, Gershwin leaves Clojure to resolve symbols. In Gershwin word definitions, quotations, at the Gershwin REPL and when a Gershwin script file is evaluated, Gershwin first attempts to resolve a symbol as a var referencing a Gershwin word, and only if that fails falls back to Clojure resolution. Gershwin var's and Clojure var's are kept separate (albeit with name munging), so you don't need to worry (too much) about name clashes.

To make the distinction between Gershwin word var's and regular Clojure var's, Gershwin provides an extra reader macro `#*` for Gershwin word var's and the `require-words` function for requiring Gershwin namespaces (specifically required when using `:refer` to refer specific Gershwin words from another namespace).
