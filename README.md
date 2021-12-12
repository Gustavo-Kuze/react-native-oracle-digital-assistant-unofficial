# react-native-oracle-digital-assistant-unofficial

## Getting started

`$ npm install react-native-oracle-digital-assistant-unofficial --save`

### Mostly automatic installation

**For react-native** `<.60`

`$ react-native link react-native-oracle-digital-assistant-unofficial`

**For react-native** `>=.60`

`No extra steps`

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
