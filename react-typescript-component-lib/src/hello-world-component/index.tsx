import React from 'react';

interface HelloWorldComponentProps {
  text: string
  index: number
  isValid: boolean
}

const HelloWorldComponent: React.FC<HelloWorldComponentProps> = ({ text, index, isValid }) => {
    return (
        <p>
          {text} | {index} = {isValid ? 'This is  valid' : 'This is invalid'}
        </p>
    );
};

export { HelloWorldComponentProps, HelloWorldComponent };