import { SideBar } from "@/components/side-bar/side-bar";

export const Container = ({
    children,
}: Readonly<{
    children: React.ReactNode;
}>) => {
    return (
        <div className="flex w-full p-4">
            <SideBar />

            <div className="w-full flex flex-col items-center ml-2">
                {children}
                {/* <Card className='w-3/4 p-4'>{children}</Card> */}
            </div>
        </div>
    );
};
