import { test } from "../api/TestApi";
import BasicLayout from "../layouts/BasicLayout";

const MainPage = () => {
  const buttonClickEvent = () => {
    test().then((data) => {
      console.log(data);
    });
  };

  return (
    <BasicLayout>
      <h1>Hello Javis</h1>
      <button onClick={buttonClickEvent}>버튼</button>
    </BasicLayout>
  );
};

export default MainPage;
