<!-- Modal -->
  <div class="modal-dialog modal-dialog-centered" id="ModalAggiungiUtente"role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="ModalAddUserTitle">Aggiunti nuovo utente</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
       <form>
       		<div class="form-group">
       			<label for="nomeUtente">Nome utente</label>
    			<input type="text" class="form-control" id="nomeUtente" placeholder="Inserisci il nome">
    			<small id="nomeUtenteHelpBlock" class="form-text text-muted">
    				Il nome deve essere formato da almeno 3 e massimo 35 caratteri alfanumerici.
    			</small>
    			<label for="cognomeUtente">Cognome utente</label>
    			<input type="text" class="form-control" id="cognomeUtente" placeholder="Inserisci il cognome">
    			<small id="cognomeUtenteHelpBlock" class="form-text text-muted">
    				Il cognome deve essere formato da almeno 3 e massimo 35 caratteri alfanumerici.
    			</small>
    			<label for="ruoloUtente">Ruolo utente</label>
    			<input type="text" class="form-control" id="ruoloUtente" placeholder="Inserisci il ruolo">
    			<small id="ruoloUtenteHelpBlock" class="form-text text-muted">
    				Il ruolo può essere "Amministratore" o "Cameriere" o "Cassiere".
    			</small>
    			<label for="idLogin">Username utente</label>
    			<input type="text" class="form-control" id="idLogin" placeholder="Inserisci lo username">
    			<small id="idLoginHelpBlock" class="form-text text-muted">
    				Il nome utente deve contenere almeno 4 caratteri alfanumerici. Deve essere formato dalla prima lettera del nome seguita dal punto e quindi dal cognome.
    			</small>
    			<label for="passwordUtente">Password utente</label>
    			<input type="text" class="form-control" id="passwordUtente" placeholder="Inserisci la password">	
    			<small id="passwordUtenteHelpBlock" class="form-text text-muted">
    				La password deve contenere almeno 8 caratteri alfanumerici.
    			</small>
       		</div>
       </form>
      </div>
      <div class="modal-footer">


        <button type="button" class="btn btn-primary btn-modal" data-type="btn-createuser" disabled>Aggiungi</button>
        <button type="button" class="btn btn-warning" id="btn-close" data-dismiss="modal">Close</button>
        

      </div>
    </div>
  </div>

