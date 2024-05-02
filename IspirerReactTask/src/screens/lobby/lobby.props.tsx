import { useNavigate } from 'react-router';
import { NewsProps } from '../news/news.props';

export type LobbyProps = {

};

export const useLobby = () => {

    const navigate = useNavigate();
    const lobby = (props?: NewsProps) => {
        navigate('/lobby', { state: props });
    };

    return lobby;
};