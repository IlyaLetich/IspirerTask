import { useNavigate } from 'react-router';

export type NewsProps = {

};

export const useLobby = () => {

    const navigate = useNavigate();
    const lobby = (props?: NewsProps) => {
        navigate('/lobby', { state: props });
    };

    return lobby;
};