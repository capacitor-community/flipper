# Capacitor Flipper Plugin

Capacitory community plugin for [Flipper](https://fbflipper.com/).

## Maintainers

| Maintainer    | GitHub                                      | Social                                           | Sponsoring Company |
| ------------- | ------------------------------------------- | ------------------------------------------------ | ------------------ |
| Priyank Patel | [priyankpat](https://github.com/priyankpat) | [@priyankpat\_](https://twitter.com/priyankpat_) | Ionic              |

Mainteinance Status: Actively Maintained

# Requirement

Download [Flipper](https://fbflipper.com/), available for Linux/Windows/Mac or run `brew cask install flipper` on Mac

## Installation

To use npm

```bash
npm install @capacitor-community/flipper
```

To use yarn

```bash
yarn add @capacitor-community/flipper
```

Sync native files

```bash
npx cap sync
```

On iOS, no further steps are needed.

On Android, register the plugin in your main activity:

```java
import com.getcapacitor.community.flipper.Flipper;

public class MainActivity extends BridgeActivity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Initializes the Bridge
    this.init(
        savedInstanceState,
        new ArrayList<Class<? extends Plugin>>() {

          {
            // Additional plugins you've installed go here
            // Ex: add(TotallyAwesomePlugin.class);
            add(Flipper.class);
          }
        }
      );
  }
}
```

## Configuration

No configuration required for this plugin.

## Supported methods

| Name         | Android | iOS | Web |
| :----------- | :------ | :-- | :-- |
| initialize   | ✅      | ✅  | ❌  |
| emulateCrash | ✅      | ❌  | ❌  |

## Example

### [Click here](https://github.com/priyankpat/capacitor-plugins-example/tree/flipper) for example code

```bash
git clone https://github.com/capacitor-community/flipper.git
git checkout flipper
```

## Usage

```typescript
import { Plugins } from "@capacitor/core";

const { Flipper } = Plugins;

/**
 * This method will configure and initialize the flipper package.
 * @param enabled - boolean true/false to enable/disable flipper
 *        network - boolean true/false to enable network plugin
 *        crash_report - boolean true/false to enable crash reporting
 *        layout_inspector - boolean true/false to enable layout inspector
 *        database - boolean true/false to enable database plugin (sqlite)
 *        database_path - custom database path if database is not stored in application context (Android)
 * @returns void
 * Make sure you have flipper running in background or restart the app for plugins to communicate with flipper
 */
Flipper.initialize({
  enabled: true,
  network: true,
  crash_report: true,
  layout_inspector: true,
  database: true,
});

/**
 * This method will trigger a custom crash notification.
 * @param none
 * @returns void
 */
Flipper.emulateCrash({
  message: "Forced Crash",
});
```
