import styled from "styled-components";

export const WrapperPopap = styled.div<{
    isActive: boolean;
    isNight: boolean;
}>`
    width: 100vw;
    height: 100vh;
    top: 0;
    z-index: 11;
    left: 0;
    background-color:${({ isNight }) => isNight ? "rgba(0,0,0,0.7)" : "rgba(0,0,0,0)"};
    position: fixed;
    display: flex;
    justify-content: center;
    align-items: center;
    display: ${({ isActive }) => isActive? "flex" : "none"};
    pointer-events: ${({ isActive }) => isActive? "all" : "none"};
    transition: 0.5s;
`