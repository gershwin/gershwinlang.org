---
title: Introducing Gershwin
---

## Introducing Gershwin ##

Welcome to the home of the Gershwin programming language. Gershwin is currently in a pre-alpha state and under heavy, active development. Both the language and this site will evolve considerably in the next few months.

It's always helpful to have deadlines. This year's Emerging Languages Camp (Strange Loop preconf) has accepted [my talk proposal on Gershwin](https://thestrangeloop.com/sessions/gershwin-stack-based-concatenative-clojure). I submitted that talk proposal based on a prototype version of Gershwin that used Clojure as a library (hosted [here](https://github.com/semperos/gershwin)), but have since reimplemented the language as a direct extension of the Clojure reader and compiler. This reimplementation of Gershwin is hosted [here](https://github.com/gershwin/gershwin) as a part of the [Gershwin Github organization](https://github.com/gershwin).

I want to underscore the fact that Gershwin is only an extension to Clojure. Gershwin takes no credit for Clojure's built-in data structures, concurrency support, namespacing, or any of the other excellent features that make Clojure a joy to develop with. Gershwin adds a few new features to Clojure's reader and compiler infrastructure to allow developers to write programs in a concatenative style, and then leverages those features itself to provide a core API that allows end-users of the language to employ the concatenative approach. Gershwin's data stack is currently implemented as a Clojure vector; Gershwin words and quotations compile directly into Clojure functions; many of Gershwin's core words (functions) delegate directly to identically named ones in Clojure core.

In the same vein, those aspects of Gershwin that are not direct descendants of Clojure are largely inspired by or copied from the [Factor programming language](http://factorcode.org/). From its own description, Factor is "an experiment to build a modern, useful Concatenative language with strong abstraction capabilities and good support for interactive development" [<i class="icon-external-link"></i>](http://concatenative.org/wiki/view/Factor/FAQ/Why%3F). Though Factor was originally implemented by Slava Pestov as a JVM language, he eventually [abandoned](http://concatenative.org/wiki/view/Factor/Java%20Factor) that implementation, primarily due to design limitations imposed by the JVM.

Therefore, from a pragmatic perspective, I have developed Gershwin to have a concatenative, Factor-like language that targets the JVM, for all the reasons any language targets the JVM. However, I also do not intend to program on the JVM without Clojure semantics. And as Fogus [recently commented on Twitter](https://twitter.com/fogus/status/341888294742794240):

> If you're creating a new JVM language and /don't/ outright steal Clojure's persistent structs then you're just crazy insane.

I'm sure I can find other ways to be crazy insane, but not following Clojure's good lead in implementing Gershwin will not be one of them.
