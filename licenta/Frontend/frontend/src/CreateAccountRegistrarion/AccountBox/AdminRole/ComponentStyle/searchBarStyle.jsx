const SearchBarContainer = styled(motion.div)`
    display: flex;
    flex-direction: column;
    width: 50em;
    height: 3.8em;
    background-color: #ffff;
    border-radius: 6px;
    box-shadow: 0px 2px 12px 3px rgba(0, 0, 0, 0.14);
`;

const SearchInputContainer = styled.div`
    width: 97%;
    min-height: 4em;
    display: flex;
    align-items: center;
    position: relative;
    padding-left:15px;
`;

const SearchInput = styled.input`
    width: 100%;
    height: 100%;
    outline: none;
    border: none;
    font-size: 21px;
    color: #bebebe;
    border-radius: 6px;
    font-weight: 500;
    background-color: transparent;


    &:focus {
        outline: none;
        &::placeholder{
            opacity: 0;
        }
    }

    &::placeholder{
        color: #bebebe;
        transition: all 250ms ease-in-out;
    }
`;


const SearchContentList = styled.div`
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    padding: 1em;
    overflow-y: auto;
    align-items: center;
`;

const LineSeparator = styled.span`
    display: flex;
    min-width: 100%;
    min-height: 2px;
    background-color: #d8d8d878;
`;

const LoadingWrapper = styled.div`
    width: 100%;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
`;

const SearchIcon = styled.span`
    color: #bebebe;
    font-size: 23px;
    margin-right: 7px;
    margin-top: 6px;
    vertical-align: middle;

`;

const CloseIcon = styled(motion.span)`
    color: #bebebe;
    font-size: 23px;
    vertical-align: middle;
    transition: all 200ms ease-in-out;
    cursor: pointer;
    
    &:hover{
        color: #dfdfdf;
    }
`;

const containerVariants = {

    expanded: {
        height: "20em",
    },

    collapsed: {
        height: "3.8em"
    }
}

const WarningMessage = styled.div`
    color: #a1a1a1;
    font-size: 14px;
    display: flex;
    align-self: center;
    justify-self: center;
`;