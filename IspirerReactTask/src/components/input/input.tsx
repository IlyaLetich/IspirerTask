import { ChangeEvent, FC, HTMLAttributes, ReactNode, memo, useCallback, useState } from "react";
import { InputButton, InputContainer, InputStyled } from "./input.styles";
import eyeLogo from "../../images/eye.png"
import noEyeLogo from "../../images/no-eye.png"
import { Text } from "../text";
import { theme } from "../../themes/theme";
import { Column } from "../column";
import { error } from "console";

export type InputProps = {
  themeColor?: string;
  textColor?: string;
  borderColor?: string;
  placeholder?: string;
  width?: number;
  value?: string;
  type?: "text" | "password";
  maxLength?: number;
  minLength?: number;
  required?: boolean;
  disabled?: boolean;
  error?: null | string;
  setValue?: (value: string) => void;
} & HTMLAttributes<HTMLInputElement>;

export const Input: FC<InputProps> = memo(({borderColor, themeColor,textColor,error,  setValue, disabled, value, width, required, placeholder, type, minLength, maxLength,...rest }) => {

  const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
    if (setValue !== undefined) {
      setValue(event.target.value);
    }
  };

  const [inputWathc, setWatch] = useState<Boolean>(false);

  const onClickWatch = useCallback(() => {
    setWatch(!inputWathc);
  }, [inputWathc]);

  return (
    <>
      <InputContainer width={width}>
        <InputStyled
          themeColor={themeColor}
          textColor={textColor}
          borderColor={borderColor}
          maxLength={maxLength}
          minLength={minLength}
          placeholder={placeholder}
          disabled={disabled}
          required={true}
          onChange={handleInputChange}
          value={value}
          type={type === "text" || type === undefined ? "text" : inputWathc ? "text" : "password"}
          {...rest}
        >
        </InputStyled>
        {type === "password" &&
          <InputButton onClick={onClickWatch} src={inputWathc ? noEyeLogo : eyeLogo}/>
        }
      </InputContainer>
      {error && <Text style={{marginTop: 5}} themeFont={theme.fonts.m1} themeColor={'red'}>
        {error}
      </Text>}
    </>
  );
});

