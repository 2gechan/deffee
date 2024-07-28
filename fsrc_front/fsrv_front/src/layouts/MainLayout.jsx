const MainLayout = ({ children }) => {
  return (
    <>
      <div className="bg-white w-full">
        <main className="m-10"></main>
        {children}
      </div>
    </>
  );
};

export default MainLayout;
