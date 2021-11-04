---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}


# Developer Guide - NUSTracker

--------------------------------------------------------------------------------------------------------------------

## **Introduction**

NUSTracker is a standalone desktop app that aims to help event directors and administrative personnel of
NUS student organisations to manage the organisation of undergraduate student events more easily.

As this app is geared towards the tech-literate generation, it is designed to use
a Command-Line Interface (CLI) to speed up usage for fast typists, while still making use of a
clean Graphical User Interface (GUI).

NUSTracker allows event directors to manage student events attendance information of the large undergraduate
student base.


--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/se-edu/addressbook-level3/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.


**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point).

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)


The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `StudentListPanel`, `StatusBarFooter`, `EventListPanel`, etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.


The `UI` component uses the JavaFx UI framework and heavily utilizes CSS as well. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2122S1-CS2103T-T11-1/tp/blob/master/src/main/resources/view/MainWindow.fxml) is specified in [`MainWindow.fxml`](https://github.com/AY2122S1-CS2103T-T11-1/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The 'UI' component makes use of .css files to change the theme of the application.

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Student` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a student).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete s/e0123456")` API call.

![Interactions Inside the Logic Component for the `delete s/e0123456` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the address book data i.e., 
  * all `Student` objects (which are contained in a `UniqueStudentList` object).
  * all `Event` objects (which are contained in a `UniqueEventList` object).
* stores the currently 'selected' `Student` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Student>` that can be 'observed' 
* similarly, stores the currently 'selected' `Event` objects as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Event>` that can be 'observed' 
* the UI is bound to these lists so the UI can automatically update when the data in either list changes.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

**<<<Check if this part can delete**
<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Student` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Student` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

**>>>**

</div>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both address book data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `nustracker.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.


**<<< Check if we are going to keep this `undo` feature >>>**

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th student in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new student. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the student was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
    * Pros: Easy to implement.
    * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
    * Pros: Will use less memory (e.g. for `delete`, just save the student being deleted).
    * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* manpower and administrative personnel of student organisations.
* has a need to manage a significant number of NUS undergraduate students attendance information.
* needs an app to collate NUS student events and their corresponding manpower
  information in one place.
* prefer desktop apps over other types.
* can type fast.
* prefers typing to mouse interactions.
* is reasonably comfortable using CLI apps.

**Value proposition**:

* able to store NUS undergraduate student information.
* create and track attendance for NUS student events.
* manage attendance more efficiently and faster than a typical mouse/GUI driven app.
* automates a bulk of attendance management processes.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                     | So that I can…​                                                        |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *`  | potential user                             | easily get the app running                  | quickly start using the App                 |
| `* * *`  | new user                                   | view a tutorial of basic commands           | learn how to use the software quickly |
| `* * *`  | new user                                   | view help screen                            | quickly refer to commands |
| `* * *`  | user                                       | view all the student data                   | keep track of all NUS undergraduate students                                   |
| `* * *`  | user                                       | view all events                             | keep track of the student events that the user manages                                   |
| `* * *`  | user                                       | exit the software                           | close the software |
| `* * *`  | user                                       | create a new student                        | add students newly admitted to NUS into the database |
| `* * *`  | user                                       | delete a student                            | delete students who have graduated from the database |
| `* * *`  | user                                       | create a new event                          | add new events to be managed using the app |
| `* * *`  | user                                       | delete an event                             | remove events that are no longer relevant |
| `* * *`  | user                                       | test the app using sample data              | test the app and its commands easily before loading in real student data |
| `* *  `  | intermediate user                          | edit student data                           | update previous mistake in data entry or update a change in student data |
| `* *  `  | expert user                                | export email of selected students           | use the email list to mass email relevant students |
| `* *  `  | expert user                                | export event data file                      | easily share or transfer event data to load in another computer |
| `* *  `  | expert user                                | export student data file                    | easily share or transfer the student database to load in another computer |
| `* *  `  | user                                       | blacklist students from events              | track which students are blacklisted from attending events |
| `* *  `  | user                                       | remove students from an event's blacklist   | allow that student to attend the event  |
| `* *  `  | user                                       | load student data from external file        | quickly add a large number of students |
| `* *  `  | user                                       | load event data file                        | update the list of events being managed in database |
| `* *  `  | user                                       | filter students by event                    | find students attending a particular event |
| `* *  `  | user                                       | filter students by year                     | select students who have certain years of seniority in NUS |
| `* *  `  | user                                       | view event list of student                  | see what events a student has attended before or are attending |
| `* *  `  | user                                       | filter students by major                    | find students of a particular major |
| `* *  `  | user                                       | filter students by faculty                  | find students from a particular faculty |
| `* *  `  | user                                       | open settings menu                          | adjust settings |
| `* *  `  | user                                       | toggle between the events and students list | quickly and efficiently view the list I want to see |
| `* *  `  | user                                       | use auto save                               | save the database constantly and automatically                             |
| `*    `  | expert user                                | mass edit student data                      | update data of similar students more efficiently |


*{More to be added}*

### Use cases

For all use cases below, the _System_ is **NUSTracker** and the _Actor_ is the **user**, unless specified otherwise

<br><br>

**<u>Use case UC1 - Add a student</u>**

**Preconditions:** -

**Guarantees:** New student info is saved, and displayed.

**MSS:**
1. User types in command
2. AddressBook adds the user to the address book
3. AddressBook displays that user has been added, and corresponding details

   Use case ends

**Extensions:**
* 1a. User types in an invalid format
    * 1a1. AddressBook shows an error message, and displays the correct format to use.

      Use case ends.

<br><br>

**<u>Use case UC2 - Delete a student</u>**

**Preconditions:** -

**Guarantees:** Student is deleted from program, and display is updated

**MSS:**

1. User requests to delete a specific student in the list
2. AddressBook deletes the student, and informs user

    Use case ends.

**Extensions:**
* 1a. User types in an invalid format
    * 1a1. AddressBook shows an error message, and displays the correct format to use.

      Use case ends
  
* 1b. The student does not exist.
  * 1b1. AddressBook shows an error message, informing the user that the student does not exist.

    Use case ends.

<br><br>

**<u>Use case UC3 - Filter students by name</u>**

**Preconditions:** -

**Guarantees:** The list of students is not altered

**MSS:**

1.  User requests to filter students by multiple names
2.  AddressBook displays a list of students whose names contain the specified keywords

    Use case ends.

**Extensions:**
* 2a. The list of students is empty.

  Use case ends.
 
* 2b. The AddressBook does not find any students with from the given keywords.
 
    * 2b1. AddressBook displays an empty list.

        Use case ends.
     
* 2c. User types multiple prefixes to filter by.

    * 2c1. Address book shows an error message.
    
        Use case ends.

<br><br>


**<u>Use case UC4 - Add an event</u>**

**Preconditions:** -

**Guarantees:** New event info is saved, and displayed

**MSS:**

1. User types in command
2. AddressBook adds the event to the address book
3. AddressBook displays that the event has been added, and corresponding details

    Use case ends.

**Extensions:**
* 1a. User types in an invalid format
  * 1a1. AddressBook shows an error message, and displays the correct format to use.

    Use case ends.


* 1b. An event with the same name already exists in the address book.
  * 1b1. AddressBook shows an error message, informing the user that an event with the same name already exists.

    Use case ends.

<br><br>

**<u>Use case UC5 - Delete an event</u>**

**Preconditions:** -

**Guarantees:** Event is deleted from program, and display is updated

**MSS:**

1. User types in command
2. AddressBook deletes the event from the address book
3. AddressBook displays that the event has been deleted, and corresponding details

   Use case ends.

**Extensions:**
* 1a. User types in an invalid format

<br><br>

**<u>Use case UC6 - Blacklist a student</u>**

**Preconditions:** -

**Guarantees:** Student is blacklisted from the event, and display is updated

**MSS:**

1. User types in command
2. AddressBook adds student to the event's blacklist
3. AddressBook displays that the student has been blacklisted

   Use case ends.

**Extensions:**
* 1a. User types in an invalid format
    * 1a1. AddressBook shows an error message, and displays the correct format to use.

      Use case ends.


* 1b. The event does not exist in the address book.
    * 1b1. AddressBook shows an error message, informing the user that the event does not exist.

      Use case ends.


* 1c. The student is already in the event's blacklist.
    * 1b1. AddressBook shows an error message, informing the user that the student is already in the event's blacklist.

      Use case ends.

<br><br>

**<u>Use case UC7 - Whitelist a student</u>**

**Preconditions:** -

**Guarantees:** Student is removed from the event's blacklist, and display is updated

**MSS:**

1. User types in command
2. AddressBook removes student from the event's blacklist
3. AddressBook displays that the student has been removed from the blacklist

   Use case ends.

**Extensions:**
* 1a. User types in an invalid format
    * 1a1. AddressBook shows an error message, and displays the correct format to use.

      Use case ends.


* 1b. The event does not exist in the address book.
    * 1b1. AddressBook shows an error message, informing the user that the event does not exist.

      Use case ends.


* 1c. The student is not in the event's blacklist.
    * 1b1. AddressBook shows an error message, informing the user that the student is not in the event's blacklist.

      Use case ends.

<br><br>

**<u>Use case UC8 - Enroll a student into an event</u>**

**Preconditions:** -

**Guarantees:** The student gets enrolled into the specified event if both the student and event exist. 

**MSS:**

1.  User requests to enroll a student into an event.
2.  nustracker updates the event to have this student as a participant.
3.  nustracker updates the student to be enrolled into this event.
4.  nustracker shows that the student has now been enrolled.

    Use case ends.

**Extensions:**
* 1a. User types in an invalid format.
    * 1a1. nustracker shows an error message, and displays the correct format to use.
      Use case ends.

* 1b. The specified student does not exist in nustracker.
    * 1b1. nustracker shows an error message, informing the user that the specified student does not exist.
    Use case ends.

* 1c. The specified event does not exist in nustracker.
    * 1c1. nustracker shows an error message, informing the user that the specified event does not exist.
  Use case ends.

* 1d. The specified student is currently already enrolled into the event.
    * 1d1. nustracker shows an error message, informing the user that the specified student is already currently enrolled.
  Use case ends.

<br><br>

**<u>Use case UC9 - Remove a student from an event</u>**

**Preconditions:** -

**Guarantees:** The student is not enrolled into the specified event after the use case ends.

**MSS:**

1.  User requests to remove a student from an event.
2.  nustracker updates the event to remove this student from being a participant.
3.  nustracker updates the student to not be enrolled into this event anymore.
4.  nustracker shows that the student has now been removed from the event.

    Use case ends.

**Extensions:**
* 1a. User types in an invalid format.
    * 1a1. nustracker shows an error message, and displays the correct format to use.

      Use case ends.
    
* 1b. The specified student does not exist in the AddressBook.
    * 1b1. nustracker shows an error message, informing the user that the specified student does not exist.

  Use case ends.

* 1c. The specified event does not exist in the AddressBook.
    * 1c1. nustracker shows an error message, informing the user that the specified event does not exist.

  Use case ends.

* 1d. The specified student is not currently enrolled into the event.
    * 1d1. nustracker shows an error message, informing the user that the specified student cannot be removed from the event as the student is not a participant.

  Use case ends.

<br><br>

**<u>Use case UC10 - Exporting emails</u>**

**Preconditions:** -

**Guarantees:** -

**MSS:**

1. User requests to export emails from a list of students and provides the name of the file to save it in.
2. nustracker exports the emails and places them in a save file.
3. nustracker shows a confirmation message that the emails have been successfully exported.

    Use case ends.

**Extensions:**
* 1a. User types in an invalid format.
    * 1a1. nustracker shows an error message, and displays the correct format to use.
      Use case ends.

* 1b. User types in an invalid name for the save file.
    * 1b1. nustracker shows an error message, informing the user that the file name they have chosen is invalid.
  Use case ends.
    
<br><br>



### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
1.  Should be able to hold up to 40000 students and still be able to respond to user input within 2 seconds.
1.  Should be able to hold a reasonable amount of events without any degradation in performance.
1.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
1.  Should be able to save all data on local storage.
1.  The application should work without having to be installed first.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Command-Line Interface**: A text-based user interface allowing users to interact with applications by typing commands.
* **Graphical User Interface**: A graphical user interface that allows users to interact with applications through the use of interactive visuals.
* **NUS**: An abbreviation for National University of Singapore.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

    1. Download the jar file and copy into an empty folder

    1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

    1. Resize the window to an optimum size. Move the window to a different location. Close the window.

    1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a student

1. Deleting a student

   1. Test case 1: `delete id/e3223223`<br>
       Prerequisites: Load sample data or ensure a student with the student id e3223223 exists in the address book.
   
       Expected: Student with student ID "e3223223" is deleted. Details of the deleted student shown in the status message. Timestamp in the status bar is updated.

   2. Test case: `delete id/e0000000`<br>
     Prerequisites: Load sample data or ensure no student has the student id e0000000 exists in the address book.

      Expected: No student is deleted. Error details shown in the status message. Status bar remains the same.

   3. Test case: `delete id/e12345`<br>
   Expected: Incorrect student id format. Error details shown in the status message. Status bar remains the same.

   4. Other incorrect delete commands to try: `delete id/`, `delete id/abc`, `delete id/[incorrect student id format]` (correct student id format : `eXXXXXXX` where X is an integer from 0-9)<br>
      Expected: Similar to previous.

3. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

    1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_

