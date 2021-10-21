---
layout: page
title: wowsiddanth's Project Portfolio Page
---

### Project: NUSTracker

_NUSTracker_ is an event-management software that was built specifically for the NUS student/manpower executive in mind. It is
a brownfield project that extends the AddressBook3 project, which is around 10kLoC.

* **New Feature**: Redesign Person class to behave like a Student class
  * **What it does**: Adds the necessary fields and methods so that the Person class behaves like a Student (_has a student ID, email, etc_)
  * **Justification**: This features redesigns the Person, so that it meets the specification of an NUS student, taking part in an event.
  * **Highlights**: This change affected almost many other class, as Person class is a fundamental component of the initial AddressBook3 project. It
  was definitely challenging adding these new attributes and tracing and fixing these regressions.
  * **Credits**: -


* **New Feature**: Ability to add/delete student information
  * **What it does**: Adds the relevant methods and attributes that allowed for the deletion of every attribute of a student
  * **Justification**: This allows for the user to edit a specific wrong inputted field, instead of deleting and re-entering that particular student.
  * **Highlights**: This change affected many other classes, especially so the test cases. It was tough remaking a plethora of test cases and adding in new ones, to ensure stability.
  * **Credits**: -


* **New Feature**: Remake GUI to match mockup
  * **What it does**: Remakes the current GUI so that it matches the mockup proposed in _Figma_.\
  * **Justification**: To beautify the product, and allow the user to have an elegant user experience.
  * **Highlights**: JavaFX, unlike many other frameworks for designing GUIs, is not as active as the rest. Thus, it was definitely 
  tedious reading many articles and documentations posted by Oracle to gain an intuitive understanding. Additionally, it was also
  difficult as I had to learn CSS from scratch, from resources such as the Mozilla Foundation, etc.
  * **Credits**: JavaFX documentation by Oracle, and the CSS resources by the Mozilla Foundation.
   

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=wowsiddanth&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~test-code~other~functional-code&since=2021-09-17)
 

* **Enhancements to existing features**:
  * Updated the GUI to match mockup on _Figma._
  * Modified Person class to be Student class.


* **Documentation**:
  * User Guide:
    * Added documentation for the features `add` and `delete` [\#27](https://github.com/AY2122S1-CS2103T-T11-1/tp/pull/27)A 
  * Developer Guide:
    * Edit Developer Guide to add use cases for add/delete student information


* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#58](https://github.com/AY2122S1-CS2103T-T11-1/tp/pull/58), [\#48](https://github.com/AY2122S1-CS2103T-T11-1/tp/pull/48), [\#46](https://github.com/AY2122S1-CS2103T-T11-1/tp/pull/46), [\#33](https://github.com/AY2122S1-CS2103T-T11-1/tp/pull/33).


* **Tools**:
  * Integrated a new Github plugin (CircleCI) to the team repo

