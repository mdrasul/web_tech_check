
# autotest_web
Welcome to **autotest_web**, home of the QA CareTalks web test automation!  
  
## What is it?
**autotest_web** is a repository containing test code only, and can only run in conjunction with the [amwell_autoframework](https://bitbucket.americanwell.com/projects/QAAUT/repos/amwell_autoframework/browse).  Tests in this repository use [Selenium](https://www.seleniumhq.org/) to run in a browser either locally on your laptop or on our pre-configured [Selenium grid](https://wiki.americanwell.com/display/QA/List+of+Selenium+Grid+Machines).  

## What does it do?
It helps you to test all of the things!  See the following WIKI page for an updated list of existing test tickets and descriptions:

https://wiki.americanwell.com/display/QA/Summary+of+automated+tests+in+autotest_web

## Where do I start? 
Your first goals are to get your environment set up and then run an existing test.  Here's how:

- Watch the [video](https://wiki.americanwell.com/display/QA/Tutorial+Videos/) to see how to set up your environment and run your first test.
- Read the <a href="https://wiki.americanwell.com/display/QA/Server+Prerequisites+for+Automation">Server Prerequisites for Automation</a> page for important server setup details.  

Or jump right in:
- Download and install the [IntelliJ](https://www.jetbrains.com/idea/download/) community version and Java JDK (Red Hat Open JDK 1.8 is the preferred option) if you haven't got it already.  
- Download the latest [ChromeDriver](https://sites.google.com/a/chromium.org/chromedriver/downloads) and Firefox geckodriver executables for your platform and make them accessible by adding their locations to your **System** PATH.  
- Clone [autotest_web](https://bitbucket.americanwell.com/projects/QAAUT/repos/autotest_web/browse) from bitbucket.  
- You can also clone the [amwell_autoframework](https://bitbucket.americanwell.com/projects/QAAUT/repos/amwell_autoframework/browse) but don't need to until you're ready to create your own tests.  The latest platinum version of the framework is pulled in automatically by the [build.gradle](https://bitbucket.americanwell.com/projects/QAAUT/repos/autotest_web/browse/build.gradle) file.  
- Open the project in Intellij and import the project as a Gradle project.  
- Copy the **required** config.yaml file necessary for testing by running the command `./gradlew copyArtifactoryConfig`. This will copy the `config.sample` file from the `amwell_autoframework` Artifactory jar, copy it to the src/test/resources dir and rename the file extension to `.yaml` 
- Copy the existing [src/main/resources/reportportal.properties.sample](https://bitbucket.americanwell.com/projects/QAAUT/repos/autotest_web/browse/src/main/resources/reportportal.properties.sample) and rename to **src/main/resources/reportportal.properties** in order to run Gradle test suite tasks. This will also generate a report to the Report Portal web app at http://bos-ts-rp-01:8080
- Create a new IntelliJ task: Run >> Edit Configuration >> '+' >> gradle: name: smoketest gradle project: autotest_web task: caretalksSmoke  
- Edit the **config.yaml** file to your chosen settings, such as `server` for your system in test, and you should now be able to run the smoke test. You can execute the suite with the following command: `./gradlew caretalksSmoke` 

## Now try it on the Grid
We have our own in-house Selenium grid. The value is set in the `config.yaml` file under the property `seleniumHub: bos-qa-slnm-01:4444`

Running tests on the grid is generally faster and is also a good way to generate results without having a bunch of windows popping up all over your desktop.

## What next?
Try running the ```regression.xml``` regression suite with the command `./gradlew caretalksRegression`.  It contains many more tests.  It should run fine on the grid, but if you want to run it locally you will need to lower the thread count in the `regression.xml` file to `4` unless you have a very powerful laptop.

## Can I write my own tests?
Yes!  Here's how:  
  
 - Download the [amwell_autoframework](https://bitbucket.americanwell.com/projects/QAAUT/repos/amwell_autoframework/browse) and open this in a separate instance of IntelliJ the same way you opened the test repository earlier.  
 - In here you will have the ability to edit existing methods and page objects and create new ones.  
 - After making changes to the framework to suit your testing needs, run the **autoJar** task to build a jar file of the framework which contains your changes  
 - Copy the new **build/libs/amwell-autoframework-1.0-SNAPSHOT.jar** from the framework repo to the [**jars/**](https://bitbucket.americanwell.com/projects/QAAUT/repos/autotest_web/browse/jars) directory of your test repo (usually autotest_web).  
 - Run the following Gradle task `./gradlew copyJarConfig`. What this does is copy over the **config.sample** file from the imported jar (*be forewarned that your existing file will be overwritten. It is recommended to rename your **config.yaml** to **local-config.yaml** to avoid an unintentional overwrite*), renames it **config.yaml**, and places it in the **src/test/resources** dir to allow you to test properly. In addition, this task also comments out the **build.gradle** dependency for the Artifactory jar so that it will not override the expected changes made from the imported jar  
    
Now the tests will run using *your* framework jar containing whatever changes you made.   Try to make sure these temporary changes don't get pushed to a pull request or it will be rejected.
  
**Note**: When making further changes, first delete the old jar from the **jars/** directory *before* copying over the new one.  IntelliJ sometimes has trouble noticing the change if you simply overwrite the old jar with a new one.

## Development
### Feature Branches
All work should have an associated JIRA ticket when possible. If it is a minor fix, use the label `NOJIRA` when creating your feature branch. The format of the feature branch should be as follows:

`review/QAAUT-1234-short-description-of-work`

### GIT Flow (General)
Make sure you branch off the `platinum` branch. If you have new tests to add for your new changes, name your testing project branch the same as this project's branch. It is **imperative** that both the framework and autotest_web branches have the same exact name. This is to ensure that the Bamboo CI build picks up the correct jar file for the autotest_web branch to run its smoke tests.
```
git checkout platinum
git checkout -b review/QAAUT-1234-my-feature-branch
```
When you are satisfied with the changes, add the modified or new files to staging (ready to commit status).

First, check the status of your changes first (do this often!) You will be presented with a list of untracked and tracked changes:
`git status`

You can add one or a few files at a time (separate by space)
`git add path/to/changed/file path/to/new/file`

Or you can add all your changes at once:
`git add -A` or `git add .`

Do a `git status` again to ensure you have all the files you intend for your commit. When you are satisfied use `git commit` which will bring up the interactive VI(M) editor. Follow this format for your commit message. Be sure to add the JIRA ticket(s) for any work included into the commit message; this will sync with the Jira ticket to provide a link to the BitBucket commit and pull request:
```
QAAUT-1234 Short Jira Title
- ExampleClass was updated due to dependencies on refactor of method Foo.bar()
- added new method doThis() in ExampleTwoClass to do a new thing
- added test cases: QAAUT-1000, QAAUT-1001, QAAUT-1002
```

After your commit message is completed. It is time to push your changes for a code review (or just to push up for safe-keeping!). Make sure to rebase your branch with `platinum` to ensure you are up to date with the latest changes from `platinum`:
```
git checkout platinum //make sure your changes are either stashed or committed before you can checkout a new branch
git pull //this gets all the latest changes from origin to your local platinum
git checkout review/QAAUT-1234-my-feature-branch
git rebase platinum //this places all the latest changes from platinum behind your changes
```
Now you are ready to send your branch upstream. You will only need to use this specific command the first time you are pushing up a new branch to origin:
`git push -u origin review/QAAUT-1234-my-feature-branch`

All new commits from this branch (after staging your changes) will use the command: `git push`
The new commit's message does not need the header as shown in the commit message example. Use the following format:
```
- fixed method Foo.boo() that was always returning true
```

If you have a typo or some insignificant change, you can just amend your previous commit after adding your changes to staging: `git commit --amend` 
This will bring up the interactive editor. You can modify your message to include the change or keep the same message. The next command is to send this amended commit upstream since you are sending up the same commit hash: `git push -f`

Outside of this, if you're havin' GIT problems, I feel bad for you, son, I got 99 problems but a commit ain't one. (Yes, that was bad) In any case, please reach out to anyone in the TestOps or Integration/Regression squad for help.

Please be proactive with your PR. If you get a comment, respond as soon as you can. If you are a reviewer, get used to marking the PR as "Needs Work" if you have comments/concerns on the functionality and the coding standards are not met.

When making changes to your PR, make sure they are **new** commits (refrain from `git commit --amend` unless, as stated above, it's for minor changes) so we can keep track of the changes. When you get the required number of approvals, then you must squash your commits and push the branch back up for a final review before merging.

You can use the interactive editor to squash your commits with the command `git rebase -i HEAD~n` where `n` is the number of commits from your branch that you wish to squash.

### Web Core PRs
Required reviewers are `Gary Cao, Christopher Roberts, and Kent Wood`.  Optional and suggested reviewers are `Chan Suom, Daniel Vainshtein, and Ken Burwood`.

**NOTE:** If there are infrastructural changes for any pull request, an approval from `Chan Suom` is required. If you are unsure what is infrastructural, please reach out for clarification as it relates to your work.

Please keep the following points in mind when submitting your changes for approval:
- Be sure to include the ticket number in both PR's title so that the relation between the 2 can be seen clearly. 
    
- Most importantly, do not merge either PR to platinum until **BOTH** the framework and test PR's have been approved. At which point, merge them both at once to reduce the chances of a breaking build from someone else pushing up a branch. Each branch push to the **amwell_autoframework** project will trigger a build.

Happy coding!
