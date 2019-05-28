import React from 'react';
import SockJsClient from 'react-stomp';
 
class SampleComponent extends React.Component {
  // eslint-disable-next-line no-useless-constructor
  constructor(props) {
    super(props);
  }
 
  sendMessage = (msg) => {
    this.clientRef.sendMessage('/topics/public', msg);
  }
 
  render() {
    return (
      <div>
        hello
        <SockJsClient url='http://localhost:8080/app' topics={['/topics/public']}
            onMessage={(msg) => { console.log(msg); }}
            ref={ (client) => { this.clientRef = client }} />
      </div>
    );
  }
}

export default SampleComponent;