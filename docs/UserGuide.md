---
layout: page
title: User Guide
---

**NUSTracker** is a desktop application for managing large NUS-based student organizations, and is optimized for use via the CLI or Command Line Interface. Nonetheless, it is supported by a detailed and elegant Graphical User Interface (GUI).


* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `nustracker.jar`, found under Releases

1. Copy the file to the folder you want to use as the _home folder_ for NUSTracker.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add n/John Doe m/CS id/e1283011 y/2 p/81231293 e/johndoe@example.com`** : Adds a contact named `John Doe` to the Address Book.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

Command: `help`

### Adding a student: `add`

Adds a student to NUSTracker

Command: `add n/STUDENT_NAME m/MAJOR id/STUDENT_ID y/YEAR p/NUMBER e/EMAIL [ev/EVENT] [t/TAGS]`

Examples:
* add n/John Doe m/CS id/e1283011 y/2 p/81231293 e/johndoe@example.com

**Formatting**:

- Major are to be specified via acronyms. The following acronyms are valid:
  1. CS (Computer Science)
  2. BZA (Business Analytics)
  3. IS (Information Systems)
  4. ISEC (Information Security)

- The NUS NetID must be specified exactly. {eXXXXXXX}

- Year must be specified as a single number.


_**Coming soon:** Additional field ev/EVENT so the user can directly tag an event to a student._

### Creating an event: `create`

Adds an event to NUSTracker

Command: `create n/EVENT_NAME d/EVENT_DATE t/EVENT_TIME`

Examples:
* create n/Orientation Camp d/01-08-2022 t/1000

**Formatting**:

- Event name cannot be empty.

- Event date is in the format `DD-MM-YYYY`.
  - DD represents the day (from 01-31 inclusive)
  - MM represents the month (from 01-12 inclusive)
  - YYYY represents the year

- Event time is in 24 hour time `HHMM`.
  - HH represents the hour (from 00-24 inclusive)
  - MM represents the minute (from 00-59 inclusive)
  - **Note:** if HH is `24`, MM has to be `00`. (`2400`)

### Listing all student

Shows a list of all students in the address book.

Format: `list`

### Filter students: `filter`

Filters students whose data contains the given field.

Command: `filter [id/STUDENT_ID] [ev/EVENT_NAME] [n/STUDENT_NAME]`

* You can only filter by one field (i.e. filter only by student id or event name).
* The search is case-insensitive. e.g. `ev/ifg` will match `ev/IFG`

Examples:
* `filter id/e1234567` returns the information of the student with the id e1234567.
* `filter n/John` returns the students with the name John.
* `filter ev/IFG` returns a list of students who are tagged to the IFG event.

_**Coming soon:** Additional fields to filter by._

_**Coming soon:** Filter by multiple fields._

### Editing a student : `edit`

Edits an existing student in the address book.

Command: `edit INDEX [n/NAME] [m/MAJOR] [id/ NUS NetID] [y/YEAR] [p/PHONE] [e/EMAIL] [ev/EVENT] [t/TAGS]…​`

* Edits the student at the specified `INDEX`. The index refers to the index number shown in the displayed student list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the student will be removed i.e adding of tags is not cumulative.

Examples:
* `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st student to be `91234567` and `johndoe@example.com` respectively.
* `edit 2 n/Betsy Crower e/betsycrower@example.com` Edits the name of the 2nd student to be `Betsy Crower` and edits email.

### Deleting a student : `delete s/`

Deletes the specified student from the address book.

Command: `delete s/INDEX`

* Deletes the student at the specified `INDEX`.
* The index refers to the index number shown in the displayed student list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete s/2` deletes the 2nd student in the address book.
* `find John` followed by `delete s/1` deletes the first student in the results of the `find` command.

### Deleting an event : `delete ev/`

Deletes the specified event from the address book.

Command: `delete ev/EVENT_NAME`

* Deletes the event with the specified `EVENT_NAME`.

Examples:
* `delete ev/Orientation` deletes the event with the name `Orientation`.
* `delete ev/Sports Camp` deletes the event with the name `Sports Camp`.

### Exiting the program : `exit`

Exits the program.

Command: `exit`

### Saving the data

NUSTracker data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

NUSTracker data is saved as a JSON file. Advanced users are welcome to update data directly by editing that data file.

_Please edit carefully! NUSTracker will start a fresh run if errors are present!_

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous NUSTracker home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/STUDENT_NAME m/MAJOR id/STUDNET_ID y/YEAR p/COUNTRY_CODE NUMBER e/EMAIL [ev/EVENT_NAME]`
**Create event** | `create n/training d/09-10-2021 t/1800`
**List students** | `students`
**List events** | `events`
**Add student to event** | `enroll id/STUDENT_ID ev/EVENT_NAME`
**Delete student from event** | `remove id/STUDENT_ID ev/EVENT_NAME`
**Delete student** | `delete s/INDEX`
**Delete event** | `delete ev/EVENT_NAME`
**Display commands** | `help`
**Exit** | `exit`
