import { FC, memo } from 'react';
import { NewsProps } from './news.props';
import { NewsView } from './news.view';
;

export const News: FC<NewsProps> = memo(() => {

  return (
      <NewsView/>
    );
});
