# groovy-numerals

A small library for generating the cardinal name of a number ("numerals to words"), usable both as a command-line tool and as a library from your own code. This is the original Groovy implementation.

> This Groovy version is preserved as the historical origin of the project. Active development and the artifact published to Maven Central now live in the pure-Java port at [josuemb/org.numerals](https://github.com/josuemb/org.numerals). This repository keeps the full commit history from 2011 onward, but is not published to Maven Central.

Supported languages, selected by locale: Spanish (`es`), English (`en`, plus British English `en_GB` with the "and" connector), Portuguese (`pt`), Italian (`it`), Catalan (`ca`), Galician (`gl`), Romanian (`ro`), Occitan (`oc`), Asturian (`ast`), and Aragonese (`an`). Maximum length: 24 digits.

## Requirements

- Java 21 or newer
- No separate Gradle install required: the project ships the Gradle wrapper (`gradlew` / `gradlew.bat`)

## Build

The project uses Gradle. Use the bundled wrapper (`./gradlew` on Linux/macOS, `gradlew.bat` on Windows); if you prefer a system-wide install, follow the [Gradle installation guide](https://docs.gradle.org/current/userguide/installation.html) and replace `./gradlew` with `gradle`.

Build a distributable package for console use:

- Zip package:

  ```bash
  ./gradlew distZip
  ```

- Tar GZ package:

  ```bash
  ./gradlew distTar
  ```

The package is written under `build/distributions/`. Uncompress it (zip or tar gz) to obtain a runnable directory named `numerals-<version>` containing a `bin/` directory with the launcher scripts.

## Usage

After uncompressing the distribution, run the console version from its `bin/` directory:

- Windows:

  ```bat
  numerals number
  ```

- Linux / macOS / other:

  ```bash
  ./numerals number
  ```

Example (Linux execution with the JVM default locale set to `es_MX`):

```text
user@host:/tmp/numerals-0.1/bin$ ./numerals 123

[123]=[ciento veintitrés]
```

## Related projects

- [josuemb/org.numerals](https://github.com/josuemb/org.numerals) — the pure-Java port of this project; the version published to Maven Central under `io.github.josuemb:numerals`.