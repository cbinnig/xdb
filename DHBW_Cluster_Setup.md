# How to setup the DHBW clusters #

Config for your local machine:

append at the end of /etc/hosts<br />
141.72.16.226   blade01<br />
141.72.16.229   blade02<br />
141.72.16.233   blade03<br />
141.72.16.236   blade04<br />
141.72.16.235   blade05<br />
141.72.16.238   blade06<br />
141.72.16.239   blade07<br />
141.72.16.241   blade08<br />
141.72.16.243   blade09<br />
141.72.16.244   blade10<br />

and create a new file at /etc/clusters with the following content:<br />
dhbwCluster1 141.72.16.226 141.72.16.229  141.72.16.233 141.72.16.236 141.72.16.235 141.72.16.238 141.72.16.239 141.72.16.241 141.72.16.243 141.72.16.244

Please do not forget to install csshX on your machines for administrating the machines


---


## Cluster info: ##
xdb is installed in ~/xdb/ <br />
mysql is installed on /var and has approx. 63GB storage left <br />
stefan will provide an iscsci which we need to attach

The best way to copy xdb.jar ist to scp a new jar to the master and then use csshX to copy it via scp to all other machines  <br />
on you local machine: scp lib/xdb.jar USERNAME@141.72.16.226:~/xdb/lib  <br />
on the cluster machines simultanously via csshX: scp 141.72.16.226:~/xdb/lib/xdb.jar ./lib/  <br />