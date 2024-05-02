import { FC, memo, useCallback } from "react";
import { StyledHeader } from "./header.styles";
import { Column } from "../column";
import { Text } from "../text";
import { theme } from "../../themes/theme";
import { useLobby } from "../../screens/news/news.props";

export type HeaderProps = {

};

export const Header: FC<HeaderProps> = memo(() => {
    const lobby = useLobby();
    const onLobby = useCallback(() => {
        lobby({});
    }, [lobby])

    return (
      <StyledHeader>
          <Column style={{height: '100%'}} verticalAlign='center' horizontalAlign='center'>
              <Text 
                  onClick={onLobby}
                  style={{cursor: 'pointer'}}
                  themeFont={theme.fonts.b1} 
                  themeColor={theme.colors.surface}>
                      Hacker News
              </Text>
          </Column>
      </StyledHeader>
    )
})

