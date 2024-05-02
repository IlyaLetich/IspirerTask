import { FC, HtmlHTMLAttributes, ReactNode, memo, useEffect } from "react";
import { WrapperPopap } from "./popup.styles";
import { Surface } from "../surface";
import { Row } from "../row";
import { ImageButton } from "../image-button";

import closeIcon from "../../images/close.png";
import { Line } from "../line";
import { Spacing } from "../spacing";
import { theme } from "../../themes/theme";

type PopupProps = {
    header?: ReactNode
    isActive : boolean,
    closePopup: () => void,
    isNight?: boolean,
    children?: ReactNode,
    paddingBeforeName?: number,
}  & HtmlHTMLAttributes<HTMLElement>;;
  
export const Popup: FC<PopupProps> = memo(({ isNight = true, paddingBeforeName, header, isActive, closePopup,children,...rest }) => {
    useEffect(() => {
        const handleScroll = (event: Event) => {
          if (isActive) {
            event.preventDefault();
            event.stopPropagation();
          }
        };
        if (isActive) {
          document.body.style.overflow = "hidden";
          document.body.addEventListener("scroll", handleScroll, {
            passive: false,
          });
        } else {
          document.body.style.overflow = "";
          document.body.removeEventListener("scroll", handleScroll);
        }
        return () => {
          document.body.style.overflow = "";
          document.body.removeEventListener("scroll", handleScroll);
        };
    }, [isActive]);
    
    return (
        <WrapperPopap isNight={isNight} isActive={isActive} onClick={closePopup}>
            <Surface style={{width: 'auto', padding: '0px'}} width={100} onClick={(e) => e.stopPropagation()} {...rest}>
                <Row padding={[10,20]} width={'100%'} horizontalAlign={"space-between"} verticalAlign={"center"}>
                  {header}
                  <ImageButton width={20} height={20} onClick={closePopup} src={closeIcon}/>
                </Row>
                {header && <Line color={theme.colors.grey} height={1}/>}
                <Spacing variant='Column' themeSpace={5}/>
                {children}
            </Surface>
        </WrapperPopap>
    )
});
