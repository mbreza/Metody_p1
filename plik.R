fun1<-function(x) x+x^2
fun2<-function(x) x^0*6.080371088054329+x^1*-5.61669728426394+x^2*1.3793845344699087+x^3*-0.09758268674415288
x<-seq(1.0, 7.0, by = 0.0001)
matplot(x,cbind(fun1(x),fun2(x)), ylim=c(-20, 20), type="l") ; abline(h=0)
