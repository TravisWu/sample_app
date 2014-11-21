library(deSolve)
parameters = c(j=10, f = 2, B_w = 00000.58, B_r =00000.62 ,c =0.0005, r=0.002, d1=0.00003, z=0.00005, D=0.00001, u = 0.5)
state = c(S=100, I_w= 20, I_r=2)
TB_model = function(t, state, parameters){
  f <- unname(parameters['f']) # Release from jail
  j <- unname(parameters['j']) # Rate into Jail
  B_w <- unname(parameters['B_w']) # Beta w
  B_r <- unname(parameters['B_r']) # Beta r 
  u <- unname(parameters['u']) # rate of mutation 
  c <- unname(parameters['c']) # recovery for drug resistance 
  r <- unname(parameters['r']) # recovery rate 
  d1 <- unname(parameters['d1']) # Death by disease  I_w
  z <- unname(parameters['z']) #Death by disease I_r 
  D <- unname(parameters['D']) # Natural death 
  S <- unname(state['S']) # Number of Suceptible 
  I_w <- unname(state['I_w']) # number of Infected without resistance 
  I_r <- unname(state['I_r']) # number of Infected resistance 
 

  dSdt = j - B_w*S*I_w - B_r*S*I_r - f*S + r*I_w + c*I_r - D*S
  dI_wdt = B_w*S*I_w - r*I_w - u*I_w - d1*I_w - D*I_w -(f*I_w)
  dI_rdt = B_r*S*I_r - c*I_r + u*I_w - z*I_r - D*I_r - (f*I_r)
  
 
  return(list(c(dSdt, dI_wdt, dI_rdt)))
}

times = seq(0, 100, 1)


output_TB_model <- ode(y=initial_condition, times=times, func=TB_model, parms=parameters)
tail (output_TB_model)

plot(output_TB_model[,c('time','S')], type='l', lty=1, ylab='fraction of infected I(t)', main='Graphical effect of overcrowding')
lines(output_TB_model[,c('time','I_w')], lty=2)