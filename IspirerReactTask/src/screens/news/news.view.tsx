
import { FC, memo, useCallback, useEffect, useState } from 'react';
import { Wrapper } from '../../components/wrapper';
import { Header } from '../../components/header';
import { Column } from '../../components/column';
import { Surface } from '../../components/surface';
import { Row } from '../../components/row';
import { Button } from '../../components/button';
import { Text } from '../../components/text';
import { theme } from '../../themes/theme';
import { useLobby } from './news.props';
import { Spacing } from '../../components/spacing';
import { useStores } from '../../hooks/use-stores';
import { useError } from '../error/error.styled';
import { useParams } from 'react-router-dom';
import { observer } from 'mobx-react-lite';

export type NewsViewProps = {

};

export const NewsView: FC<NewsViewProps> = observer(({}) => {
  const { newsStore } = useStores();
  const { id } = useParams();
  
  const error = useError();
  const onError = useCallback(() => {
    error({});
  }, [error])

  const getInfo = useCallback(async () => {
    if(id === undefined) {
      onError();
      return;
    }
    await newsStore.fetchStories(parseInt(id),onError);
  }, [newsStore,onError,id])

  useEffect(() => {
    getInfo();
  }, [getInfo]);

  const lobby = useLobby();
  const onLobby = useCallback(() => {
    lobby({});
  }, [lobby])

  const onLink = useCallback((value: string) => {
    window.open(value);
  }, [])


  return (
    <Wrapper>
      <Header/>
      <Column padding={20}>
        <Surface style={{borderWidth: 2}} borderColor={theme.colors.background} padding='20px'>
          <Row style={{width: '100%'}} horizontalAlign='space-between' verticalAlign='center'>
            <Text themeColor={theme.colors.background} themeFont={theme.fonts.b1}>
              The content of the news
            </Text>
            <Button onClick={onLobby} themeColor={theme.colors.primary} height={30} width={100}>
              <Text themeColor={theme.colors.surface} themeFont={theme.fonts.m1}>
                Back
              </Text>
            </Button>
          </Row>
        </Surface>
        <Spacing themeSpace={20} variant='Column'/>
        <Surface style={{borderWidth: 2,overflow: 'auto'}} borderColor={theme.colors.background} padding='20px'>
          <Text themeColor={theme.colors.background} themeFont={theme.fonts.m1}>
            {JSON.parse(JSON.stringify(newsStore.news))?.title}
          </Text>
          <Spacing themeSpace={10} variant='Column'/>
          <Row verticalAlign='flex-end'>
            <Text themeColor={theme.colors.background} themeFont={theme.fonts.m1}>
              {JSON.parse(JSON.stringify(newsStore.news))?.by}
            </Text>
            <Spacing themeSpace={20} variant='Row'/>
            <Text themeColor={theme.colors.background} themeFont={theme.fonts.r1}>
              { new Date(JSON.parse(JSON.stringify(newsStore.news)).time  * 1000).toLocaleString()}
            </Text>
          </Row>
          <Spacing themeSpace={5} variant='Column'/>
          <Text onClick={() => onLink(JSON.parse(JSON.stringify(newsStore.news))?.url)} themeColor={theme.colors.background} themeFont={theme.fonts.ri1}>
            Link: {JSON.parse(JSON.stringify(newsStore.news))?.url}
          </Text>
          <Spacing themeSpace={5} variant='Column'/>
          <Text themeColor={theme.colors.background} themeFont={theme.fonts.m1}>
            Count of comments: {JSON.parse(JSON.stringify(newsStore.news)).kids?.length}
          </Text>
        </Surface>
      </Column>
    </Wrapper>
  );
});