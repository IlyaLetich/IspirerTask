import styled, { keyframes } from "styled-components";
import { theme } from "../../themes/theme";

export const WrapperNews = styled.div`
    display: flex;
    box-sizing: border-box;
    width: 100%;
    flex-direction: row;
    align-items: center;
    overflow: auto;
    justify-content: space-between;

    @media (max-width: ${theme.toMobileSize + 'px'}) {
        flex-direction: column;
    }
`;

export const ContainerInfoNews = styled.div`
    display: flex;
    box-sizing: border-box;
    flex-direction: column;
    align-items: flex-start;
    justify-content: space-between;
    overflow: auto;

    @media (max-width: ${theme.toMobileSize + 'px'}) {
        margin-bottom: 20px;
        width: 100%;
    }
`;

const spin = keyframes`
    0% {
        transform: rotate(0deg);
    }
    100% {
        transform: rotate(360deg);
    }
`;

export const CircularProgressCustom = styled.div`
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
    width: 50px;
    height: 50px;
    border-radius: 50%;
    border: 4px solid rgba(0, 0, 0, 0.1);
    border-top-color: ${theme.colors.primary}; 
    animation: ${spin} 1s linear infinite;
`;