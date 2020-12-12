# Feedback for Wim I
+ short and precise quickstart example for Spring Boot newbies
+ Great to start early with nice css

- adjust explanation of beans and components and what CI is
- maybe quick template HTML to code in parallel a little earlier, and explain what the `th` is
- idea (to server side render newbies): high level picture how the processing  (template + input -> final HTML -> browser)
- Webpack instead of Gulp?
- Maybe move NPM installation to a central setup chapter?
- The Frontend Maven Build Config in pom.xml is hard to copy. Maybe split it and maybe spaces instead of tabs to fit it to the screen
- Mixing npm with npx (maybe quick explanation what npx is)
- Trying to get the Tailwind Application Shell: https://tailwindui.com/components/application-ui/application-shells/sidebar redirects me to their pricing
- Copying the whole template index.html (as I couldn't access Tailwind without paying them) is a little tedious. Maybe reduce the initial template and add stuff step-by-step

+ great refactoring effort and introduction to fragments
+ full file name on top of each code example is extremely helpful

- after JS refactoring part: Uncaught (in promise) ReferenceError: sidebarMenu is not defined (code examples lack the function sidebarMenu -> plain copy and paste failed)

- wrong file name for User Thymeleaf view Page 99, it conains the UserController fully qualified class name
- idea: short note on how to work with this book: Each chapter has first some intro (don't start copying here) and then we'll apply the knowledge -> you can now start copying
- short and concise chapter about i18n -> nice
- more reasoning behind JPearl, why do we want the primary to be early-initialized
- injecting the EM should also work with @Autowired -> maybe confusion for new devs
- do I need a plugin for this .env to work? Tried it on Ubuntu Linux
- you could enrich "See the sources on GitHub for the full details of all classes. Note that we also added Guava as a dependency (for the toString() implementation):" with an link to GitHub
- "The UserService currently only has a single method createUser with a single argument CreateUserParameters:" at this stage it already had two methods in the book
- not sure what's going on but with postgres:12 I always got auth issues connection to db with password. Wasn't also able to do it via IDEA, postgres:11 works however. Did they change some auth mechanisms? The integration test with Testcontainers works however
- not sure if that's missing/important but the book talks about the InMemoryUniqueIdGenerator class but does not show the @Bean definition (or I overlooked it). So once you say in the book, now start with local,init-db profiles, this bean is missing in the context
- getFullName() is introducted between Chapter 9 and 10
- page 148, the UserService interface now shows createUser and getUsers, but lacks the already mentioned and implemented getAllUsers method
- nice sophisticated paging solution, I guess helpful for a lot of developers
- Used PDF version: 2020-11-27.pdf
- maybe add the full-path to edit.html that the reader knows it's inside the users folder
- adding the @NotExistingUser on top of the form data class is missing, however it comes in the next chapter about validator groups
- awesome features with the validation groups I wasn't aware of yet, saves DB load in this example
- reasoning why the birthday is not in any group? Is the intention to demo the Default validation group?
- site 165 shows `@NotNull` on the `phoneNumber`, but the sources for chapter use `@NotBlank` -> with `@NotNull` no valid error message is shown for me for the phoneNumber and I was getting crazy what's the difference because I couldn't find any in the template :D
- UserNotFoundException is new? Maybe hint or small code example
- parameters.update() function is missing in book, however you have tagged it in the source code
- "update happens automatically by JPA/Hibernate" -> but only if wrapped inside a transaction. Not sure if I forgot it to add but at this stage in the book I don't have it yet at my UserService
- getUser(UserId) is added without extending the interface
- page 197: Add the path to the fragment as it's not stated how to name it and when using the fragment in the view, maybe add a sentence to remove the old title
- idea: any external JS + CSS as WebJars? Helps at least me while reading the book in train without internet connection :D
- whenever you say "Add this to the template", it's not clear where exactly to add it sometimes also id does not matter much where. I know that I can easily look this up but still a short hint would be nice to switch constantly between book, IDEA and original source code. Example: the hidden version field
- page 207: @Validated(CreateUserValidationGroupSequence.class), should be still @Validated(ValidationGroupSequence.class) at this point in the book
- really nice error pages, with the general shell layout of the application this looks really sophisticated
- the delete modal markup code is quite big, not sure if it's worth to explain it with more babysteps or e.g. remove the CSS classes from the book source code for a better reading experience
- page 253, we also have to wrap the table header in <th:block sec>, right?
- DatabaseInitializer why are there @Nonnull annotations (not part of the book, but saw it in the sources)
- After adding the new factory method to create User, you could mention to also update the tests as otherwise the project won't compile
- page 290 "... JUnit test and this will start our complete application, including an embedded Tomcat to server the HTML pages" -> serve instead of server
- you updated from chapter 14 -> 15 the titles.html fragment and added CSS ids to identify the elements during tests, you might want to mention this

## Next Feedback (Tips & Tricks) Version 1.0.0


# Questions

- how to create the nice screenshots with background
