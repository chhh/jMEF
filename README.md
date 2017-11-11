## jMEF - A Java library to create, process and manage mixtures of exponential families
The original project is at http://vincentfpgarcia.github.io/jMEF<br/>
The original license (Boost) is kept, no major changes to the project were made.

### Modificaitons
Changed directory and package structure so the code can be easily imported into IDEs. All packages are lowercase
to avoid problems with windows/linux file system case-sensitivity.<br/>
Included patches from other forks of the original projects.

### Build
 * Either: `mvn clean package`(using maven3), the output will be in `<repo_path>/target/`
 * Or load the project in Intellij IDEA and click `Menu -> Build -> Build Artifacts ->
 All artifacts -> Rebuild` the output will be in `<repo_path>/out/artifacts/`
