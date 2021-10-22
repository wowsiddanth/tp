---
layout: page
title: Elvis Teo's Project Portfolio Page
---

### Project: NUSTracker

NUSTracker is a standalone desktop app that aims to help event directors and administrative personnel of
NUS student organisations to manage the organisation of undergraduate student events more easily.
It is built on top of **[AddressBook - Level 3](https://se-education.org/addressbook-level3)**, which is a desktop address book application used for teaching Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to enroll students into events.
  * What it does: Allows the user to enroll students into the events that they want to participate in.
  * Justification: This feature forms part of the base product as it allows students to be used together with events.
  * Highlights: The implementation was challenging as it required interacting between 2 different sets of classes which have a wide array of subclasses - Student and Event, and making them work together.
  It was also tricky to devise a way to load the enrolled events of a student from the JSON file, as all students and events have to be loaded first before this can happen.

* **New Feature**: Added the ability to remove students from events.
  * What it does: Allows the user to remove students from the events that they are currently enrolled in.
  * Justification: This feature forms part of the base product as it allows students to be used together with events.
  * Highlights: The implementation was of similar type and difficulty to that of the enroll feature.

* **Code contributed**: [RepoSense link]()

* **Project management**:
  <!--- * Managed releases `v1.3` - `v1.5rc` (3 releases) on GitHub --->

* **Enhancements to existing features**:
  <!--- Changed the edit command to identify students by student ID instead of by index in the GUI's list.

  * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
  * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]()) --->

* **Documentation**:
  * User Guide:
    * Added documentation for the features `enroll` and `remove`
  * Developer Guide:
    * Added Introduction section.
    * Added Target User Profile and Value Proposition. (From Team Discussions)
    * Adapted discussed User Stories into Markdown table form and added it.
    * Added Non-Functional Requirements and Glossary.

* **Community**:
  <!---
  * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())
  --->

* **Tools**:
  <!---
  * Integrated a third party library (Natty) to the project ([\#42]())
  * Integrated a new Github plugin (CircleCI) to the team repo
  --->

