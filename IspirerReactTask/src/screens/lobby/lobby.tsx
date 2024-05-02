import { FC, memo } from 'react';
import { LobbyProps } from './lobby.props';
import { LobbyView } from './lobby.view';
;

export const Lobby: FC<LobbyProps> = memo(() => {

  return (
      <LobbyView/>
    );
});
