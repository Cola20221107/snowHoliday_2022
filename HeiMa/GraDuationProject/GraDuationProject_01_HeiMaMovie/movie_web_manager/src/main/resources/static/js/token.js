//请求拦截
axios.interceptors.request.use(
    config => {
        console.log(localStorage.getItem("token"));
        config.headers.token = localStorage.getItem("token"); // 在请求头中携带token
        console.log(config);
        return config;
    },
    error => {
        return Promise.reject(error);
    }
);
//响应拦截
axios.interceptors.response.use(
    response => {
        if (response) {
            return response
        }
    },
    error => {
        //当响应码为401时,给出页面提示,并且清空token,刷新页面
        if (error.response) {
            if (error.response.status == 401) {
                localStorage.removeItem("token");
                window.top.location.href = "./login.html";
            }
        }
        return Promise.reject(error.response.data)
    }
);

