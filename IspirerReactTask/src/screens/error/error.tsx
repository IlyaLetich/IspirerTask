import { FC, memo } from 'react';
import { ErrorProps } from './error.props';
import { ErrorView } from './error.view';
;

export const Error: FC<ErrorProps> = memo(() => {

  return (
      <ErrorView/>
    );
});
