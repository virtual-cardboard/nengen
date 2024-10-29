# nengen
The 4th iteration of virtual cardboard's Java game engine. This LWJGL-based engine is an organized yet flexible alternative to LibGDX.

## Publishing to GitHub Packages

To publish the project to GitHub Packages, follow these steps:

1. Create a `settings.xml` file in the `.m2` directory with the following content:

```xml
<settings>
  <servers>
    <server>
      <id>github</id>
      <username>${env.GITHUB_ACTOR}</username>
      <password>${env.GITHUB_TOKEN}</password>
    </server>
  </servers>
</settings>
```

2. Run the following command to deploy the project:

```sh
mvn deploy
```
