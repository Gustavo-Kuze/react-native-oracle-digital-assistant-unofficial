# react-native-oracle-digital-assistant-unofficial

**IMPORTANT NOTE**: For now, this library only supports Android's client SDK. 

iOS support will come in a future update.

## Getting started

Install Node dependency

```$ npm install react-native-oracle-digital-assistant-unofficial --save```

or

```$ yarn add react-native-oracle-digital-assistant-unofficial```

### Link dependencies

**For react-native** `<.60`

`$ react-native link react-native-oracle-digital-assistant-unofficial`

**For react-native** `>=.60`

`Skip`

## Add native SDKs to native projects

### Android

1. Replace the following line in `settings.gradle`:

Replace

```
include ':app'
```

with

```
include ':app', ':com.oracle.bots.client.sdk.android.core-21.10'
```

2. Import the following dependencies in `android/app/build.gradle`:

```
implementation "androidx.swiperefreshlayout:swiperefreshlayout:1.0.0"
implementation 'androidx.preference:preference:1.1.1'

// SDK
implementation project(':com.oracle.bots.client.sdk.android.core-21.10')

// Core dependencies
implementation 'androidx.room:room-runtime:2.2.5'
implementation 'io.socket:socket.io-client:0.8.3'
implementation 'androidx.core:core:1.3.0'
```

3. Copy the native module from `node_modules/react-native-oracle-digital-assistant-unofficial/android/com.oracle.bots.client.sdk.android.core-21.10` to your project's **android** directory

## Usage

### Send a text message

```jsx
	<Button
		title="Chat"
		onPress={() => {
			OracleDigitalAssistant.sendMessage('Buy some coffee');
		}}
	/>
```

### Listen for new text messages

```jsx
import { DeviceEventEmitter } from 'react-native'
import OracleDigitalAssistant from 'react-native-oracle-digital-assistant-unofficial';

...
  useEffect(() => {
    (async () => {
      try {
        const result = await OracleDigitalAssistant.init(
          'userId',
          '2h7s92cv-d4c6-ds93-a069-f1l374932aaL',
          'oda-9928v32csde323asb5o930s1c84751f4-dd4.data.digitalassistant.oci.oraclecloud.com',
        );

        OracleDigitalAssistant.setupChatListeners();

        DeviceEventEmitter.addListener('onStatusChange', status => {
          console.log(status)
          /*
          "status" can be either of:

          DISCONNECTED
          CONNECTING
          CONNECTED
          */
        });

        DeviceEventEmitter.addListener('onMessage', message => {
          console.log(message);
          /*
          outputs:
			{
				"actions": [
						"1. New Shopping Cart",
						"2. Checkout",
						"3. SignUp",
				],
				"createdDate": "Dec 10, 2021 17:28:00",
				"footerText": "",
				"headerText": "",
				"isRead": false
			}
          */
        });
      } catch (err) {
        console.log(err);
      }
    })();
  }, []);
```
