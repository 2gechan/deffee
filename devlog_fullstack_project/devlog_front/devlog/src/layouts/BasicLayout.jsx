import MainTap from "./MainTap";
import Top from "./Top";

const BasicLayout = ({ children }) => {
  return (
    <div className="w-full flex flex-row justify-center">
      <div className="flex flex-col w-[80%]">
        <Top />
        <MainTap />
        <main>{children}</main>
      </div>
    </div>
  );
};

export default BasicLayout;
