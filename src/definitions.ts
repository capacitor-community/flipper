declare module "@capacitor/core" {
  interface PluginRegistry {
    Flipper: FlipperPlugin;
  }
}

export interface FlipperPlugin {
  initialize(options: InitializeOptions): Promise<void>;

  emulateCrash(): Promise<void>;
}

export interface InitializeOptions {
  enabled: boolean;
  network: boolean;
  crash_report: boolean;
  layout_inspector: boolean;
  database: boolean;
  database_path: boolean;
}