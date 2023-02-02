mvc lib invok


settting actioncontext
 com.webkit.web.ActionContext.createActionContext(context,request,response);



        FilterConfig  fc=new FilterConfig() {
            @Override
            public String getFilterName() {
                return "fltnm";
            }

            @Override
            public ServletContext getServletContext() {
                return reqst.getServletContext();
            }

            @Override
            public String getInitParameter(String s) {
                if(s.equals("charset"))
                return "utf8";
                return "";
            }

            @Override
            public Enumeration<String> getInitParameterNames() {
                return null;
            }
        };
        FilterDispatcher filterDispatcher = new FilterDispatcher();
        filterDispatcher.init(fc);
        filterDispatcher.doFilter(reqst, resps,new FilterChain(){
            @Override
            public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException, ServletException {

            }
        });