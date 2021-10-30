---
layout: page
title: User Guide
---

**nustracker** is a desktop application for NUS event directors and student leaders of student organizations in the School of Computing to manage students and events.

As an event director/student leader, you will be able to add students and events into **nustracker** and use it to keep track of which students are attending which event, and even blacklist students from certain events. These are just some of the features, and there will be even more coming in the future. **nustracker** is also customized to your needs, and is simple, intuitive and easy to pick up.

All these things can be done through a simple and intuitive Command Line Interface (CLI), while being complimented by a detailed and elegant application. This document will guide you through on how to start using **nustracker**.



* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Getting Started

**You finally have your hands on _*nustracker*_. Great! Let's learn how to use it now.**

1. Ensure you have `Java 11` or above installed in your Computer.

2. Download the latest `nustracker.jar`, found under [Releases](https://github.com/AY2122S1-CS2103T-T11-1/tp/releases).


3. Copy the `nustracker.jar` to the folder you want to use as the _home folder_ for nustracker.

4. Double-click the file to start the app. The GUI similar to the one shown below should appear in a few seconds. The app already contains some sample data.<br>

   ![Ui](images/Ui.png)


5. Type the command in the command box and press Enter to execute it. For example, typing `help` and pressing Enter will open the help window.<br>
 
 
   Here are some example commands you can try:

   * **`add n/John Doe m/CS id/e1283011 y/2 p/81231293 e/johndoe@example.com`** : Adds a contact named `John Doe` with the major `CS`,
      student ID `e1283011`, year `2`, phone number `81231293`, and email `johndoe@example.com` to nustracker.
   
   * **`exit`** : Exits the app.


6. For a full and comprehensive overview of each command, refer to the [Feature](#features) section!

**Now, you are ready for an unprecedented overhaul in student and event management using nustracker!**

--------------------------------------------------------------------------------------------------------------------

## Features

nustracker was designed to help the average overworked university student, who also has many important responsibilities outside the curriculum
in clubs and societies—specifically **student and event management**.


### What can nustracker do?
1. **Keep track of students**

    nustracker displays all students currently associated with the organization neatly. The students have their details
   and a profile picture (optional) listed with them as well, allowing quick and convenient access to student particulars.


2. **Keep tracks of events and event _attendance_**

    nustracker can also display the events stored, their information, and the participants taking part in those events.


4. **Keep track of event blacklists**
    
    Events often have blacklists, which can also be tracked within nustracker.


5. **Be customized to the user's liking**
    
    One of the key points of nustracker is its customization. For more information, see [Customization](#customization).


<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g. `n/NAME [m/MAJOR]` can be used as `n/John Doe m/CS` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[y/YEAR]…​` can be used as ` ` (i.e. 0 times), `y/1`, `y/ 1 2 3` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE`, `p/PHONE n/NAME` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `students`, `events`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

--------------------------------------------------------------------------------------------------------------------

## General Layout

The following is a description of the general layout of nustracker.

![general-layout](images/general-layout.png)

1. **Top bar**
    
    The top bar consists of the buttons: **Refresh, Toggle theme, Settings, Students, Events, Help, and Exit**.
    * **Refresh**
      
        Refreshes the currently displayed students/events. Press this button when you add profile pictures.

    * **Toggle Theme**
        
        Toggles the theme of nustracker, from Dark theme to Light theme, and vice versa.

    * **Settings**

         Opens the Settings Window.
     
    * **Students**

        Displays current students.

   * **Events**

     Displays current events.

   * **Help**

     Opens the Help window.

   * **Exit**

     Exit nustracker.



2. **Command bar**

    This space is for the user (you!) to type in the commands. For more information about commands, see the section below.
   <br><br>
3. **Result Display**
   
    The empty box directly below the command bar is the result display, where error/success messages are displayed.
   <br><br>
4. **Students**

    The students, their information, and the events they are taking part in are displayed here.



--------------------------------------------------------------------------------------------------------------------
## Student Commands
### Adding a student: `add`

Adds a student to nustracker

Command: `add n/STUDENT_NAME m/MAJOR id/STUDENT_ID y/YEAR p/PHONE e/EMAIL`

Formatting:

- Student name:
  - Does not need to be unique.
  - Can only contain alphanumerical characters and spaces.

- Major is to be specified via acronyms. The following acronyms are valid:
  1. CS (Computer Science)
  2. BZA (Business Analytics)
  3. IS (Information Systems)
  4. ISEC (Information Security)

- Student ID:
  - Must be specified exactly as the letter e, followed by 7 numbers. (eXXXXXXX)
  - Must be unique

- Year must be a number from 1 to 6 inclusive.
- Number must be unique.
- Email must be unique.

Examples:
* `add n/John Doe m/CS id/e7474831 y/2 p/81631293 e/JohnDoe99@example.com`
* `add n/Melissa Chong m/ISEC id/e3434827 y/5 p/90025837 e/MelissaChongxx@example.com`

<br><br>

### Listing all students: `students`

Shows all students in nustracker.

This command essentially removes any filters that have been applied with the `filter` command.

Command: `students`

<br><br>


### Editing a student : `edit`

Edits an existing student in nustracker.

Command: `edit id/ID_OF_STUDENT_TO_EDIT [n/NEW_NAME] [m/NEW_MAJOR] [id/NEW_STUDENT_ID] [y/NEW_YEAR] [p/NEW_PHONE] [e/NEW_EMAIL]`

* Edits the student who currently has the specified `STUDENT_ID`.
* **At least one of the optional fields** must be provided.
* Existing values will be updated to the input values.
* Events of a student cannot be directly edited with the `edit` command, the `enroll` and `remove` commands serve this purpose.

Examples:
* `edit id/e0322322 p/91234567 e/johndoe@example.com` Edits the phone number and email of the student with student ID `e0322322` to be `91234567` and `johndoe@example.com` respectively.
* `edit id/e0542341 n/Betsy Crower id/e0543212` Edits the name and student ID of the student with student ID `e0542341` to be `Betsy Crower` and `e0543212` respectively.
<br><br>
 
### Deleting a student : `delete`

Deletes the specified student from nustracker.

Command: `delete id/STUDENT_ID`

* Deletes the student with the specified `STUDENT_ID`.

Examples:
* `delete id/e1234567` deletes the student with the student ID `e1234567`.
* `delete id/e0589162` deletes the student with the student ID `e0589162`.
<br><br>

### Filtering students: `filter`

Filters students whose data contains the given field, the search is case-insensitive.

Command: `filter [id/STUDENT_ID [MORE_STUDENT_IDs]...] [n/STUDENT_NAME [MORE_STUDENT_NAMES]...] [m/MAJOR [MORE_MAJORS]...] [y/YEAR [MORE_YEARS]...] [ev/EVENT_NAME]`

* You can filter by:
    - Multiple student IDs.
    - Multiple student names.
    - Multiple majors.
    - Multiple years.
    - **A single** event name.

* You can only filter by one field (i.e. filter only by student ID, student name, year, major, or event name).
* You have to filter by at least 1 parameter (simply typing `filter` will not work)
* The search is case-insensitive. e.g. `n/john` will match with `n/JOHN`.
* You can search using multiple keywords except for events.
* You can search by partial keywords for names and student IDs. e.g. `n/al` will match with `n/alice` and `id/e12` will match with `id/e1234567`

Examples:
* `filter id/e1234567` returns the student with the ID e1234567 if that student exists in nustracker.
* `filter id/e1234567 e2345678` returns two students whose student IDs are e1234567 and e2345678 if they exist in nustracker.
* `filter id/e09` returns all students with student IDs that contain e09.
* `filter n/John` returns the students whose names contain John.
* `filter n/John alice` returns the students whose names contain John OR Alice.
* `filter m/CS IS` returns the students majoring in CS or IS.
* `filter y/1 2 3` returns the students in years 1, 2, or 3.
* `filter ev/orientation` returns a list of students who are tagged to the Orientation event.

_**Coming soon:** Additional fields to filter by._

_**Coming soon:** Filter by multiple fields._

<br><br>

### Delete shown students : `delfiltered`

Mass deletes all students currently shown in the student list. This is typically used after a filter
command to delete students based on a certain criteria.

Command: `delfiltered`

* This command can only be used when nustracker is showing the Student list.
* Use with caution! If the student list is not filtered before using `delfiltered`, all the students currently in
  nustracker would be deleted since all of them are shown.
* After all the filtered students are deleted, the remaining students would then all be shown in the list, if any.
* At least one student needs to be shown in the filtered list for `delfiltered` to work.

Examples:
* `filter y/4` followed by `delfiltered` will delete all students who are in Year 4, possibly because they are graduating soon.
* `filter ev/Graduation 2021` followed by `delfiltered` will delete all students that have attended the Graduation Ceremony in 2021.

<br><br>

### Blacklisting students: `blacklist`

Blacklists students from the given event by student ID. Blacklisted student IDs will not be allowed to enroll into the event.

Command: `blacklist id/STUDENT_ID ev/EVENT_NAME`

* Student ID need not exist in the database.
* Event name has to exist in the database.

Examples:
* `blacklist id/e1234567 ev/Orientation Camp` blacklists the student ID e1234567 from the Orientation Camp event.
* `blacklist id/e0000000 ev/Sports Camp` blacklists the student ID e0000000 from the Sports Camp event.

<br><br>

### Whitelisting students: `whitelist`

Whitelists students from the given event by student ID. Whitelisted student IDs are removed from the event's blacklist.

Command: `whitelist id/STUDENT_ID ev/EVENT_NAME`

* Student ID needs to be in the event's blacklist.
* Event name has to exist in the database.

Examples:
* `whitelist id/e1234567 ev/Orientation Camp` removes the student ID e1234567 from the Orientation Camp event's blacklist.
* `whitelist id/e0000000 ev/Sports Camp` removes the student ID e0000000 from the Sports Camp event's blacklist.



--------------------------------------------------------------------------------------------------------------------

## Event Commands

### Creating an event: `create`

Adds an event to nustracker

Command: `create n/EVENT_NAME d/EVENT_DATE t/EVENT_TIME`

- Event name cannot be empty.

- Event date has to be in the format `DD-MM-YYYY`.
    - DD represents the day (from 01-31 inclusive)
    - MM represents the month (from 01-12 inclusive)
    - YYYY represents the year

- Event time has to be in 24 hour time `HHMM`.
    - HH represents the hour (from 00-24 inclusive)
    - MM represents the minute (from 00-59 inclusive)
    - **Note:** if HH is `24`, MM has to be `00`. (i.e. `2400`)
    
Examples:
* `create n/Orientation Camp d/01-08-2022 t/1000` creates an event with the name Orientation Camp, date 01-08-2022 or 1 August 2022, and time 1000 hrs or 10:00 am.
<br><br>

### Listing all events : `events`

Shows all events in the app.

Format: `events`
<br><br>

### Enrolling a student : `enroll`

Enrolls the specified student into the specified event.

Command: `enroll id/STUDENT_ID ev/EVENT`

* Enrolls the student specified by student ID into the event specified by its event name.

* Event name is case-insensitive.

Examples:
* `enroll id/e0544111 ev/CS1101S Mock PE` enrolls the specified student with student ID e0544111 into the event "CS1101S Mock PE".
* `enroll id/e0322322 ev/Orientation Camp` enrolls the specified student with student ID e0322322 into the event "Orientation Camp".
  <br><br>


### Removing a student : `remove`

Removes the specified student from the specified event.

Command: `remove id/STUDENT_ID ev/EVENT`

* Removes the student specified by student ID from the event specified by its event name.

* Event name is case-insensitive.

Examples:
* `remove id/e0986472 ev/Tea Making Workshop` removes the specified student with student ID e0986472 from the event "Tea Making Workshop".
* `remove id/e0264853 ev/Fun Run` removes the specified student with student ID e0264853 into the event "Fun Run".

<br><br>

### Deleting an event : `delete`

Deletes the specified event from nustracker.

Command: `delete ev/EVENT_NAME`

* Deletes the event with the specified `EVENT_NAME`.

Examples:
* `delete ev/Orientation` deletes the event with the name `Orientation`.
* `delete ev/Sports Camp` deletes the event with the name `Sports Camp`.

<br><br>

--------------------------------------------------------------------------------------------------------------------
## Other Commands

### Viewing help : `help`

Opens the Help window

Command: `help`

<br>

### Viewing the settings: `settings`

Opens the Settings window

Command: `settings`

<br>

### Exporting Emails : `export`

Exports the emails of the students that are currently being displayed.

Command: `export fn/FILE_NAME`

- Filenames cannot contain these illegal characters:
`\ : * ? " < > | `
- Filenames cannot exceed 50 characters.

The file will be located in the `data` directory, which is where the app's save files are located.

If the "events" page is currently being shown, export will still function and will export the emails of the most recent student list.

<div markdown="block" class="alert alert-block alert-danger">
If you are exporting with a filename that already exists, the file will be overwritten!
</div >

Examples:
* `export fn/StudentsAttendingOrientation`
* `export fn/AllStudents`

<br>

### Refreshing the main window: `refresh`

Refreshes the main window. Often used to update the main window after adding profile pictures, etc.

<br>

### Changing the theme: `theme`

Toggles the theme between Dark and Light theme.

<br>

### Exiting the program : `exit`

Exits the program.

Command: `exit`

<br>

---------------------------------------------------------------------------------------
## Customization

Another key feature of nustracker is its ability to be customized greatly. This section covers how nustracker can be
customized to your liking.

### 1. Changing between Light/Dark Theme
nustracker has a light/dark theme. To toggle between the two, either type `refresh` or click the button (to the left of **Settings**) as shown in the image below and voila!
 
![change_theme](images/change_theme.PNG)

### 2. Setting a profile picture
nustracker has the ability to add a profile picture to a student. Follow the steps given to add a picture:

1. If you have not started nustracker before, run nustracker within a folder of your choice.

2. Once it runs, a folder called _**profile-pictures**_ is created within the folder that contains nustracker.

    ![profile-pictures-folder](images/profile-pictures-folder.PNG)

    *The **profile-pictures** folder is highlighted in blue*

<br>

3. Open the _**profile-pictures**_ folder and add the image you want as a profile picture. The image must be either a **.png** or a **.jpg** image.

4. <br>

   1. Rename the image to correspond to the **Student ID** of the student in nustracker that will use this profile picture.

       For example, let's say there is a student in nustracker with the Student ID **e1111111**.

      ![match-student-id](images/match-student-id.PNG)
 
      ![changed-profile-picture](images/changed-profile-picture.PNG)

      *The updated student card (belonging to student with Student ID **e1111111** in nustracker)*
      <br><br>
   3. The image will update once you **type `refresh`, click the Refresh button, or restart nustracker**.
   4. You're done!

### 3. Changing the _glow_ of the profile picture border

nustracker allows you to change the color of the glow of the profile picture.

1. Open up the **Settings** window either by clicking the **Settings** button or by typing in `settings` into the command box.
 

2. Click the dropdown menu, and pick a color. You can pick a custom color as well.

   ![pre-selected](images/pre-selected.PNG)

    *You can choose one of the pre-selected colors.* 

   <br><br>
   ![custom-color](images/custom-color.PNG)

   *Or you can  customize the color precisely.*


3. This change is **immediate**, and does not require a restart.

### 4. Customization FAQs
 
**Q**: I have added the image, but it doesn't appear. <br>

**A**:
* Please check if the image is either **.jpg** or **.png**. Other image types are NOT supported. 
* Please check if you have the correct name format used—refer to **"_Setting a profile picture_" point 3** above. 
* Please check if the image size is too small or too large. Due to the limitations of JavaFX, extreme image sizes cannot be displayed. 

If you have
exhausted all options, you can file a bug report on our [GitHub page](https://github.com/AY2122S1-CS2103T-T11-1/tp/issues).
<br>

You have now learnt to customize nustracker to its fullest. Go ahead and play around with the settings!
    

---------------------------------------------------------------------------------------
## Misc
### Saving the data

nustracker data is saved in the hard disk automatically after executing any command that changes the data. There is no need to save manually.

### Editing the data file

nustracker data is saved as a JSON file. Advanced users are welcome to update data directly by editing that data file.

_Please edit carefully! nustracker will start a fresh run if errors are present!_


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous nustracker home folder.

**Q**: I noticed a bug in nustracker. How do I report it?<br>
**A**: We are committed to delivering well-made software and to achieve that, every single bug report counts. To submit a
bug report, please visit the **Issues** page [here](https://github.com/AY2122S1-CS2103T-T11-1/tp/issues). Thank you for contributing to nustracker!

--------------------------------------------------------------------------------------------------------------------

## Command summary

### Student Commands

**Command** | **Description** | **Example** |
----------------------------|------------------------------|-------------------------------------------------------|
**add** | Adds a student | `add n/STUDENT_NAME m/MAJOR id/STUDENT_ID y/YEAR p/PHONE e/EMAIL` |
**students** | Lists students | `students` |
**edit** | Edits a student | `edit id/ID_OF_STUDENT_TO_EDIT [n/NEW_NAME] [m/NEW_MAJOR] [id/NEW_STUDENT_ID] [y/NEW_YEAR] [p/NEW_PHONE] [e/NEW_EMAIL]` |
**delete** | Deletes a student | `delete id/STUDENT_ID` |
**filter** | Filters by field  | `filter n/STUDENT_NAME [MORE_STUDENT_NAMES]...` <br> `filter id/STUDENT_ID [MORE_STUDENT_IDS]...` <br> `filter m/MAJOR [MORE_MAJORS]...` <br> `filter y/YEAR [MORE_YEARS]...` <br> `filter ev/EVENT_NAME` |
**delfiltered** | mass deletes shown students | `delfiltered` |
**blacklist** | Blacklists a student from attending an event | `blacklist id/STUDENT_ID ev/EVENT_NAME` |
**whitelist** | Whitelists a student from attending an event | `whitelist id/STUDENT_ID ev/EVENT_NAME` |


### Event Commands

**Command** | **Description** | **Example** |
----------------|-------------------------|-----------------|
**create**  | Creates an event | `create n/EVENT_NAME d/EVENT_DATE t/EVENT_TIME` _<br> Date format: DD-MM-YYYY <br> Time format: HHMM_ |
**events**  | Lists events | `events` |
**enroll**  | Adds a student to an event | `enroll id/STUDENT_ID ev/EVENT_NAME` |
**remove**  | Removes a student from an event | `remove id/STUDENT_ID ev/EVENT_NAME` |
**delete**  | Deletes an event | `delete ev/EVENT_NAME` |

### Other Commands

**Command** | **Description** | **Example** |
----------------|-------------------------|-----------------|
**help** | Opens the help window | `help` |
**settings** | Opens the settings window | `settings`|
**export** | Exports the emails of the students that are currently displayed | `export fn/FILE_NAME` |
**refresh**| Refresh the main window | `refresh` |
**theme** | Toggles the current theme | `theme` |
**exit** | Exits the app | `exit` |
