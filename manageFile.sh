#! /bin/ksh

while getopts :u:r:p: option
do
    case $option in
	u)
	    user=$OPTARG
	    ;;
	r)
	    resource=$OPTARG
	    ;;
	p)
	    password=$OPTARG
	    ;;
	*)
	    echo "invalid option $OPTION"
	    echo "usage: $(basename $0) [-u user -r resource -p password]" 1>&2
	    exit 1
    esac
done

# build command line
if [[ $# > 0 ]]
then
    commandLine="$user $resource $password"
else
    commandLine=
fi

# run
#exec mvn exec:java -Dexec.args="$commandLine"
java -jar target/ManageBlazarCryptoFile-1.0-SNAPSHOT.jar $commandLine
