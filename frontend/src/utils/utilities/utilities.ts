// const isRouteActive = (pathname: string, url: string) => {
//     // return pathname.includes(url)
//     console.log("isRouteActive: ", url.includes(pathname));
//     return url.includes(pathname);
// };

export const isRouteActive = (pathname: string, url: string) => {
    return pathname === url;
};