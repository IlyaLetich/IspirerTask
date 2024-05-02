
import { FC, memo, useCallback, useEffect, useState } from 'react';
import { Wrapper } from '../../components/wrapper';
import { Header } from '../../components/header';
import { Spacing } from '../../components/spacing';
import { Surface } from '../../components/surface';
import { Column } from '../../components/column';
import { Text } from '../../components/text';
import { theme } from '../../themes/theme';
import { Row } from '../../components/row';
import { Button } from '../../components/button';
import { ScrollView } from '../../components/scroll-view';
import { useNavigate, useParams } from 'react-router-dom';
import { CircularProgressCustom, ContainerInfoNews, WrapperNews } from './lobby.styled';
import { useStores } from '../../hooks/use-stores';

export type LobbyViewProps = {

};

export const LobbyView: FC<LobbyViewProps> = memo(({}) => {

  const { storiesStore } = useStores();

  const [isLoading,setIsLoading] = useState<boolean>(false);

  const navigate = useNavigate();

  const onClickNews = useCallback((id: number) => {
    navigate('/news/' + id)
  }, [navigate]);

  const fetchListAndItems = useCallback(async () => {
      storiesStore.setIsloaded(true);
      setIsLoading(true);
      await storiesStore.fetchStories();
      await storiesStore.fetchStoriesList();
      setIsLoading(false);
  },[storiesStore])

  useEffect(() => {
    if(storiesStore.isLoaded){
      return;
    }
    fetchListAndItems();
  }, [storiesStore,fetchListAndItems]);

  return (
    <Wrapper>
      <Header/>
      <Column padding={20}>
        <Surface style={{borderWidth: 2}} borderColor={theme.colors.background} padding='20px'>
          <Row style={{width: '100%'}} horizontalAlign='space-between' verticalAlign='center'>
            <Text themeColor={theme.colors.background} themeFont={theme.fonts.b1}>
              News List
            </Text>
            <Button onClick={fetchListAndItems} themeColor={theme.colors.primary} height={30} width={100}>
              <Text themeColor={theme.colors.surface} themeFont={theme.fonts.m1}>
                Refresh
              </Text>
            </Button>
          </Row>
        </Surface>
        <Spacing themeSpace={20} variant='Column'/>
        {
          isLoading ? <CircularProgressCustom/> : 
          <ScrollView>
            {
              storiesStore.storiesList.map(proxy => JSON.parse(JSON.stringify(proxy))).map((item) => 
                <NewsCard nameNews={item.title} nameAuthor={item.by} rank={item.score} dateNews={item.time} onClick={() => onClickNews(item.id)}/>
              )
            }
          </ScrollView>
        }
      </Column>
    </Wrapper>
  );
});

export type NewsCardProps = {
  nameNews?: string;
  nameAuthor?: string;
  rank?: number;
  dateNews: number;
  onClick: () => void;
};

export const NewsCard: FC<NewsCardProps> = memo(({onClick, nameNews, nameAuthor, rank, dateNews}) => {

  return (
    <Surface themeColor={theme.colors.foreground} padding='20px' style={{marginBottom: 20}}>
      <Column horizontalAlign='center'>
        <WrapperNews>
          <ContainerInfoNews>
            <Text themeColor={theme.colors.surface} themeFont={theme.fonts.r1}>
              Rank: {rank}
            </Text>
            <Spacing themeSpace={20} variant='Row'/>
            <Text themeColor={theme.colors.surface} themeFont={theme.fonts.r1}>
              Name news: {nameNews}
            </Text>
            <Spacing themeSpace={20} variant='Row'/>
            <Text themeColor={theme.colors.surface} themeFont={theme.fonts.r1}>
              Author name: {nameAuthor}
            </Text>
            <Spacing themeSpace={20} variant='Row'/>
            <Text themeColor={theme.colors.surface} themeFont={theme.fonts.r1}>
              Date news: {new Date(dateNews * 1000).toLocaleString()}
            </Text>
          </ContainerInfoNews>
          <Button onClick={onClick} themeColor={theme.colors.primary} height={30} width={100}>
            <Text themeColor={theme.colors.surface} themeFont={theme.fonts.m1}>
              Check
            </Text>
          </Button>
        </WrapperNews>
      </Column>
    </Surface>
  );
});