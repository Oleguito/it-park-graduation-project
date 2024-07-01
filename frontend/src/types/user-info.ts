import { ResourceAccess } from "./resourceaccess";

export type UserInfo = {
  acr: string;
  allowed_origins: string[];
  auth_time: number;
  azp: string;
  email: string;
  email_verified: boolean;
  exp: number;
  family_name: string;
  given_name: string;
  iat: number;
  iss: string;
  jti: string;
  name: string;
  preferred_username: string;
  realm_access: {
    roles: string[];
  };
  resource_access: ResourceAccess;
  scope: string;
  session_state: string;
  sid: string;
  sub: string;
  typ: string;
};