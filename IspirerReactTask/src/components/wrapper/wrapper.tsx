import { FC, HtmlHTMLAttributes, memo } from "react";
import { StyledWrapper } from "./wrapper.styles";

export type WrapperProps = {

} & HtmlHTMLAttributes<HTMLElement>;

export const Wrapper: FC<WrapperProps> = memo(({...rest }) => {
    return <StyledWrapper {...rest }/>
})

