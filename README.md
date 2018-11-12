# ACDC - Static website generation

## Build and execute project
1. Gather sources
2. Open the folder with Eclipse
3. Export -> Java -> Java runnable file
4. Open CMD and run the generated jar file with  ```java -jar <program.jar>```

## TODO List
- [x] User CMD Interface to gather information about the new post
- [x] Convert gathered information into markdown code
- [x] Transform the markdown into HTML (using Jekyll)
- [x] Create local server and open the HTML page
- [x] Push generated post on GitHub

### LOG 2018-11-14
- Add a Git class used to create git object where we can use username, password, local and distant repositery path
- Create executeCmd function that allow us to execute any cmd in a new thread
    |-> Add cmd to add, commit and push a git repositery
- Clean code
- Add javadoc

### LOG 2018-11-07
No time this week

### LOG 2018-10-31
No time this week

### LOG 2018-10-23
- Add an author to the news
- Generate markdown file and fill it with information entered by the user

### LOG 2018-10-16
- CMD interface created allowing user to enter information in order to generate the new post
- The user is able to enter all information in order to create the markdown code
