import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import { ErrorProps } from "./error.props";

export const useError = () => {

    const navigate = useNavigate();
    const lobby = (props?: ErrorProps) => {
        navigate('/error', { state: props });
    };

    return lobby;
};