library(deSolve)
parameters_TB_model = c(j=5, Z = 15, a1 = 4, a2 =3 ,c =15, r=2, d1=1, d2=3, D=5, u = 21)
initial_condition = c(S=100, I_w= 3, I_r=2, k=100)
TB_model = function(t, state, parameters){
  Z = unname(parameters['Z']) # Release from jail
  j = unname(parameters['j']) # Rate into Jail
  a1 = unname(parameters['a1']) # Beta w
  a2 = unname(parameters['a2']) # Beta r 
  u = unname(parameters['u']) # rate of mutation 
  c = unname(parameters['c']) # recovery for drug resistance 
  r = unname(parameters['r']) # recovery rate 
  d1 = unname(parameters['d1']) # Death by disease  I_w
  d2 = unname(parameters['d2']) #Death by disease I_r 
  D = unname(parameters['D']) # Natural death 
  S = unname(state['S']) # Number of Suceptible 
  k = unname(state['k']) # Carrying Capacity 
  I_w = unname(state['I_w']) # number of Infected without resistance 
  I_r = unname(state['I_r']) # number of Infected resistance 
  y = unname(state['y']) #ratio factor 
  
  N = S + I_w + I_r 
  a1 = y(N-k) + 

  dSdt = j-a1*S*(I_w/k) - a2*S*I_r - (Z*S) + r*I_w + c*I_r - D*S
  dI_wdt = a1*S*I_w - r*I_w - u*I_w - d1*I_w - D*I_w -(Z *I_w)
  dI_Rdt = a1*S*I_r - c*I_r + u*I_w - d2*I_r - D*I_r - (Z*I_r)
  
 
  return(list(c(dSdt, dI_wdt, dI_Rdt, k)))
}

times = seq(0, 100, 0.1)


output_TB_model <- ode(y=initial_condition, times=times, func=TB_model, parms=parameters_TB_model)
tail (output_TB_model)

plot(output_TB_model[,c('time','S')], type='l', lty=1, ylab='fraction of infected I(t)', main='Graphical effect of overcrowding')
lines(output_TB_model[,c('time','I_w')], lty=2)


