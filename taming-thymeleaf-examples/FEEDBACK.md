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

## Next Feedback (Chapter 11+)



# Questions

- how to create the nice screenshots with background
