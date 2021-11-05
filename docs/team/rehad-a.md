---
layout: page
title: rehad-a's Project Portfolio Page
---

### Project: nustracker

nustracker - nustracker is a desktop application that helps event directors and student leaders of student organizations in NUS School of Computing to organize and manage students attendance of events.
The code is adapted from AddressBook Level-3.

Given below are my contributions to the project.

* **New Feature**: Added the ability to filter students.
  * What it does: Allows the user to filter the list of students by different parameters. The parameters allowed are:
        * Filter by multiple names.
        * Filter by multiple Student IDs.
        * Filter by multiple years.
        * Filter by multiple majors.
        * Filter by event, which displays the students attending a certain event.
  * Justification: This feature is crucial as some other commands depend on the filtering feature, such as the export and delfiltered command which operate
  on the filtered list of students.
 
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=rehad-a&tabRepo=AY2122S1-CS2103T-T11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
    * Helped in maintaining issue tracker by closing some issues and linking them to pull requests.
    
* **Enhancements to existing features**:
    * Changed event names to be case insensitive so that the commands delete event, enroll, remove, blacklist, whitelist, and filter
      can accept case insensitive event names.
    
* **Documentation**:
  * User Guide:
    * Added documentation for the `filter` feature.
  * Developer Guide:
    * Added use cases for the `filter` feature.
  * Updated the description in the product website page and changed the visuals for the website to match nustracker's theme.  

* **Community**:
    * [Reviewed multiple PRs](https://github.com/AY2122S1-CS2103T-T11-1/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Arehad-a).
    * Reported bugs and suggestions to other team's product.