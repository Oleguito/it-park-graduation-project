import { getTokens } from "@/utils/authorizationLogic";
import { 
    introspectTokenIsActive, 
    postToIntrospectUrl 
} from "@/utils/token";

describe("Page", () => {
    it("renders a heading", () => {

        console.log(window)
        expect(1 + 1).toEqual(2)
    });
});


describe("introspectTokenIsActive", () => {
    const res = postToIntrospectUrl()
    console.log(res)
});
