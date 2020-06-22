import { WebPlugin } from '@capacitor/core';
import { FlipperPlugin, InitializeOptions } from './definitions';

export class FlipperWeb extends WebPlugin implements FlipperPlugin {
  constructor() {
    super({
      name: 'Flipper',
      platforms: ['web']
    });
  }
  
  emulateCrash(): Promise<void> {
    throw new Error("Method not implemented.");
  }

  initialize(options: InitializeOptions): Promise<void> {
    console.log(options);
    throw new Error("Method not implemented.");
  }
}

const Flipper = new FlipperWeb();

export { Flipper };

import { registerWebPlugin } from '@capacitor/core';
registerWebPlugin(Flipper);
