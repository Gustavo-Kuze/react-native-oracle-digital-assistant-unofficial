import { NativeModules } from 'react-native';

type OracleDigitalAssistantTypes = {
  init: (userId: string, channelId: string, chatServer: string,) => Promise<void>;
  isInitialized: () => Promise<Boolean>;
  setupChatListeners: () => Promise<void>;
  sendMessage: (message: string,) => Promise<void>;
};

const { OracleDigitalAssistant } = NativeModules;

export default OracleDigitalAssistant as OracleDigitalAssistantTypes;
