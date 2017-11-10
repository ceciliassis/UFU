angular.module('cpgQueue.constants', [])

.constant('AUTH_EVENTS', {
  notAuthenticated: 'auth-not-authenticated',
  notAuthorized: 'auth-not-authorized'
})

.constant('USER_ROLES', {
  admin: 'admin_role',
  public: 'public_role'
})

.constant('AUTH_ENDPOINT', {
  url: 'https://cpgqueue.fwd.wf/auth'
    // url: '/auth'
})

.constant('API_ENDPOINT', {
  url: 'https://cpgqueue.fwd.wf/cpg'
    // url: '/auth'
});