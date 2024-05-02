
import { FC, memo, useCallback } from 'react';
import { Column } from '../../components/column';
import { Text } from '../../components/text';
import { theme } from '../../themes/theme';
import { Button } from '../../components/button';
import { Spacing } from '../../components/spacing';
import { Image } from '../../components/image';

import errorImage from '../../images/error.png';
import { useLobby } from '../news/news.props';

export type ErrorViewProps = {

};

export const ErrorView: FC<ErrorViewProps> = memo(({}) => {
  const lobby = useLobby();
  const onLobby = useCallback(() => {
    lobby({});
  }, [lobby])
  
  return (
    <Column style={{height:'100vh'}} verticalAlign='center' horizontalAlign='center'>
      <Image src={errorImage} width={500} height={200} />
      <Spacing themeSpace={20} variant='Column' />
      <Text themeColor={theme.colors.background} themeFont={theme.fonts.b1}>
        Not found 404
      </Text>
      <Spacing themeSpace={20} variant='Column' />
      <Button onClick={onLobby} themeColor={theme.colors.primary} height={40} width={150}>
        <Text themeColor={theme.colors.surface} themeFont={theme.fonts.m1}>
          Back to the lobby
        </Text>
      </Button>
    </Column>
  );
});