# Capacitor Native Audio Plugin

Capacitory community plugin for native audio engine.

## Maintainers

| Maintainer | GitHub | Social | Sponsoring Company |
| -----------| -------| -------| -------------------|
| Priyank Patel | [priyankpat](https://github.com/priyankpat) | [N/A](https://twitter.com) | Ionic |

Mainteinance Status: Actively Maintained

## Installation

To use npm

```bash
npm install @capacitor/flipper
```

To use yarn

```bash
yarn add @capacitor/firebase-crashlytics
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
    this.init(savedInstanceState, new ArrayList<Class<? extends Plugin>>() {{
      // Additional plugins you've installed go here
      // Ex: add(TotallyAwesomePlugin.class);
      add(Flipper.class);
    }});
  }
}
```

## Configuration

No configuration required for this plugin.

## Supported methods

| Name  | Android | iOS | Web
| :---- | :--- | :--- | :--- |
| initialize | ✅ | ✅ | ❌ 
| emulateCrash | ✅ | ❌ | ❌ 

## Usage

```typescript
import { Plugins } from '@capacitor/core';

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
 */
Flipper.configure({
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
Flipper.emulateCrash();
```
